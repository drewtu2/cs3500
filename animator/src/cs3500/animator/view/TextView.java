package cs3500.animator.view;

import static util.MyUtil.checkNull;

import cs3500.animator.animation.AnimationSummary;
import cs3500.animator.animation.AnimationType;
import cs3500.animator.animation.IAnimation;
import cs3500.animator.animation.concrete.ColorAnimation;
import cs3500.animator.animation.concrete.MoveAnimation;
import cs3500.animator.animation.concrete.ScaleAnimation;
import cs3500.animator.model.IModelView;
import cs3500.animator.shape.IAnimatedShape;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * The text view implementation.
 */
public class TextView implements IView {

  private Appendable output;
  private int speed;

  /**
   * Creates a text view.
   *
   * @param outputFile an appendable to dump the text information.
   */
  public TextView(Appendable outputFile) {
    output = outputFile;
    speed = 1;
  }

  @Override
  public void show(IModelView myMV, int tempo) {

    checkNull(myMV);

    speed = tempo;

    Map<String, IAnimatedShape> shapeMap = myMV.getFullState();
    List<AnimationSummary> animationSummaries = new ArrayList<>();

    try {
      output.append("Shapes:\n");

      // Print every shape and generate a list of all of the animations
      for (IAnimatedShape aShape : shapeMap.values()) {
        output.append(printShapeSummary(aShape));
        output.append("\n\n");
        animationSummaries.addAll(getSummary(aShape));
      }
      output.append("\nAnimations:\n");
      Collections.sort(animationSummaries);

      for (AnimationSummary summary : animationSummaries) {
        output.append(summary.getDescription());
      }

      if (output.getClass() == FileWriter.class) {
        ((FileWriter) output).flush();
        ((FileWriter) output).close();
      }

    } catch (IOException e) {
      System.err.print("\n\nThere was an error trying to display your animation...\n\n");
    }
  }

  @Override
  public void setLooping() {
    //nothing should happen
  }

  /**
   * Dumps the shape description in the appendable.
   *
   * @param shape the shape to dump.
   */
  private String printShapeSummary(IAnimatedShape shape) {
    StringBuilder builder = new StringBuilder();

    builder.append("Name: ");
    builder.append(shape.getName());
    builder.append("\n");

    builder.append("Type: ");
    builder.append(shape.getType().toString());
    builder.append("\n");

    builder.append(shape.getType().getRef().toString());
    builder.append(": ");
    builder.append(shape.getPosition().toString());
    builder.append(", ");
    builder.append(shape.getDimension().toString());
    builder.append(", ");
    builder.append(shape.getColor().toString());
    builder.append(", Opacity: ");
    builder.append(shape.getOpacity());

    return builder.toString();
  }

  /**
   * Returns a list of animation summaries of a given shape.
   *
   * @param shape the shape containing the animation summaries.
   * @return the list of summaries.
   */
  private List<AnimationSummary> getSummary(IAnimatedShape shape) {
    checkNull(shape);

    List<AnimationSummary> animationSummaries = new ArrayList<>();
    Map<AnimationType, List<IAnimation>> animationMap = shape.getAnimations();
    AnimationSummary summary;

    for (List<IAnimation> aList : animationMap.values()) {
      for (IAnimation animation : aList) {
        summary = new AnimationSummary(animation.getStartTime(),
            reprAnimation(animation, shape.getName()),
            animation.getCreationIndex());
        animationSummaries.add(summary);
      }
    }

    return animationSummaries;
  }

  /**
   * Returns the string representation of the animation.
   *
   * @param animation the animation to represent.
   * @param shapeName the name of the shape.
   * @return String of the summary.
   */
  private String reprAnimation(IAnimation animation, String shapeName) {
    StringBuilder builder = new StringBuilder();
    builder.append("Shape ");
    builder.append(shapeName);

    switch (animation.getType()) {
      case CREATE:
        builder.append(printAnimationHelper("Appears", animation.getStartTime()));
        break;
      case COLOR:
        builder.append(printAnimationHelper(
            "changes",
            ((ColorAnimation) animation).getStartColor().toString(),
            ((ColorAnimation) animation).getEndColor().toString(),
            animation.getStartTime(),
            animation.getEndTime()));
        break;
      case DESTROY:
        builder.append(printAnimationHelper("disappears", animation.getStartTime()));
        break;
      case MOVE:
        builder.append(printAnimationHelper(
            "moves",
            ((MoveAnimation) animation).getStartPos().toString(),
            ((MoveAnimation) animation).getEndPos().toString(),
            animation.getStartTime(),
            animation.getEndTime()));
        break;
      case SCALE:
        builder.append(printAnimationHelper(
            "scales",
            ((ScaleAnimation) animation).getStartDimension().toString(),
            ((ScaleAnimation) animation).getEndDimension().toString(),
            animation.getStartTime(),
            animation.getEndTime()));
        break;

      default:
        builder.append(" does something from t=");
        builder.append(tick2Time(animation.getStartTime()));
        builder.append("s to t=");
        builder.append(tick2Time(animation.getEndTime()));
        builder.append("s");
        break;

    }

    return builder.toString();
  }

  /**
   * Returns the string given an animation.
   *
   * @param startTick the start tick.
   * @param endTick the end tick.
   * @param startState the start state as a string.
   * @param endState the end state as a string.
   * @param verb the action verb describing the animation
   * @return a string representing the animation
   */
  private String printAnimationHelper(String verb, String startState, String endState,
      int startTick, int endTick) {

    StringBuilder builder = new StringBuilder();

    builder.append(" ");
    builder.append(verb);
    builder.append(" from ");
    builder.append(startState);
    builder.append(" to ");
    builder.append(endState);
    builder.append(" from t=");
    builder.append(tick2Time(startTick));
    builder.append("s to t=");
    builder.append(tick2Time(endTick));
    builder.append("s\n");

    return builder.toString();
  }

  /**
   * Returns the string given an animation.
   *
   * @param verb the action verb describing the animation
   * @param tick the tick the action occurs
   * @return a string representing the animation
   */
  private String printAnimationHelper(String verb, int tick) {

    StringBuilder builder = new StringBuilder();

    builder.append(" ");
    builder.append(verb);
    builder.append(" at t=");
    builder.append(tick2Time(tick));
    builder.append("s\n");

    return builder.toString();
  }

  /**
   * Returns the time for a corresponding tick.
   *
   * @param tick the tick.
   * @return the time
   */
  private float tick2Time(int tick) {
    return (float) tick / (float) speed;
  }
}
