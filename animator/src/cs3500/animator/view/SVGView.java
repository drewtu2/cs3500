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
import cs3500.animator.shape.Position2D;
import cs3500.animator.shape.RGBColor;
import cs3500.animator.shape.ShapeType;
import cs3500.animator.shape.concrete.Oval;
import cs3500.animator.shape.concrete.Rectangle;
import cs3500.animator.shape.dimension.WidthHeightDim;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * Class representing the SVG view of an animator. The view will produce the corresponding SVG text
 * output and puts it into an appendable. If a file is produced, the animation can be viewed in a
 * web browser.
 */
public class SVGView implements IView {

  private int width = 1000;
  private int height = 900;
  private Appendable output;
  private float speed;
  private boolean loopable;
  private float lastAnimTime;
  private StringBuilder resetString;

  /**
   * Constructs an instance of an svgview setting the output to be the appendable. Setting speed
   * default to 1 tick/sec
   *
   * @param out Appendable to write to.
   */
  public SVGView(Appendable out, boolean loop) {
    if (out == null) {
      throw new NullPointerException();
    }

    output = out;
    speed = 1;
    loopable = loop;
    lastAnimTime = 0;
  }

  @Override
  public void show(IModelView model, int tempo) throws IOException {
    checkNull(model);

    speed = tempo;
    String endTag = "";
    Map<String, IAnimatedShape> mMap = model.getFullState();
    output.append("<svg width=\""
        + Integer.toString(width)
        + "\" height=\""
        + Integer.toString(height)
        + "\" version=\"1.1\""
        + "\n\txmlns=\"http://www.w3.org/2000/svg\""
        + ">\n\n");

    List<IAnimatedShape> shapes = new ArrayList<>(mMap.values());
    Collections.sort(shapes);

    for (IAnimatedShape curShape : shapes) {
      if (curShape.getType().equals(ShapeType.RECTANGLE)) {
        endTag = "</rect>";
        curShape = (Rectangle) curShape;
        output.append("<rect id=\"");
        output.append(curShape.getName());
        output.append("\" x=\"");
        output.append(Integer.toString(Math.round(curShape.getPosition().getX())));
        output.append("\" y=\"");
        output.append(Integer.toString(Math.round(curShape.getPosition().getY())));
        output.append("\" width=\"");
        output.append(Integer.toString(Math.round(((WidthHeightDim) curShape.getDimension())
            .getWidth())));
        output.append("\" height=\"");
        output.append(Integer.toString(Math.round(((WidthHeightDim) curShape.getDimension())
            .getHeight())));
        output.append("\" fill=\"");
        output.append(colorToRGB(curShape.getColor()));
        output.append("\" visibility=");
        if (curShape.getOpacity() > 0.0) {
          output.append("\"visible\" >");
        } else {
          output.append("\"invisible\" >");
        }
      } else if (curShape.getType().equals(ShapeType.OVAL)) {
        endTag = "</ellipse>";
        curShape = (Oval) curShape;
        output.append("<ellipse id=\"");
        output.append(curShape.getName());
        output.append("\" cx=\"");
        output.append(Integer.toString(Math.round(curShape.getPosition().getX())));
        output.append("\" cy=\"");
        output.append(Integer.toString(Math.round(curShape.getPosition().getY())));
        output.append("\" rx=\"");
        output.append(Integer.toString(Math.round(((WidthHeightDim) curShape.getDimension())
            .getWidth())));
        output.append("\" ry=\"");
        output.append(Integer.toString(Math.round(((WidthHeightDim) curShape.getDimension())
            .getHeight())));
        output.append("\" fill=\"");
        output.append(colorToRGB(curShape.getColor()));
        output.append("\" visibility=");
        if (curShape.getOpacity() > 0.0) {
          output.append("\"visible\" >");
        } else {
          output.append("\"invisible\" >");
        }
      }
      resetString = new StringBuilder();

      List<AnimationSummary> animationSummaries = getSummary(curShape);

      Collections.sort(animationSummaries);

      for (AnimationSummary summary : animationSummaries) {
        output.append(summary.getDescription());
      }

      if (loopable) {
        output.append(resetString.toString());
      }
      output.append("\n" + endTag + "\n\n");
    }

    if (loopable) {
      output.append("<rect>\n" +
          "\t<animate id=\"base\" begin=\"0;base.end\" dur=\"" + Double.toString
          (tick2Time(lastAnimTime) + 0.001) +
          "s\" attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n" +
          "</rect>\n\n");
    }

    output.append("</svg>\n");

    if (output.getClass() == FileWriter.class) {
      ((FileWriter) output).flush();
      ((FileWriter) output).close();
    }
  }

  /**
   * Appends svg formatted strings representing the animations to the appendable.
   *
   * @param s IAnimatedShape holding list of animations by type
   */
  private String showAnimations(IAnimation animation, IAnimatedShape s) throws IOException {
    String attName1 = "";
    String attName2 = "";
    StringBuilder strBuild = new StringBuilder();
//<animate attributeType="xml" begin="base.end" dur="1ms" attributeName="fill" to="rgb(255,255,255)" fill="freeze" />
    switch (animation.getType()) {
      case MOVE:
        if (s.getType().equals(ShapeType.OVAL)) {
          attName1 = "cx";
          attName2 = "cy";
        } else if (s.getType().equals(ShapeType.RECTANGLE)) {
          attName1 = "x";
          attName2 = "y";
        }
        if (typeOfMove(((MoveAnimation) animation).getStartPos(),
            ((MoveAnimation) animation).getEndPos()).contains("x")) {
          strBuild.append(printAnimationHelper(
              attName1,
              Integer.toString(Math.round(((MoveAnimation) animation).getStartPos()
                  .getX())),
              Integer.toString(Math.round(((MoveAnimation) animation).getEndPos()
                  .getX())),
              animation.getStartTime(),
              animation.getEndTime() - animation.getStartTime()));
          resetString.append(printAnimationHelper(attName1,
              Integer.toString(Math.round(((MoveAnimation) animation).getStartPos()
                  .getX()))));
        }
        if (typeOfMove(((MoveAnimation) animation).getStartPos(),
            ((MoveAnimation) animation).getEndPos()).contains("y")) {
          strBuild.append(printAnimationHelper(
              attName2,
              Integer.toString(Math.round(((MoveAnimation) animation).getStartPos()
                  .getY())),
              Integer.toString(Math.round(((MoveAnimation) animation).getEndPos()
                  .getY())),
              animation.getStartTime(),
              animation.getEndTime() - animation.getStartTime()));
          resetString.append(printAnimationHelper(attName2, Integer.toString(Math.round((
              (MoveAnimation) animation).getStartPos()
              .getY()))));
        }
        break;
      case COLOR:
        strBuild.append(printAnimationHelper(
            "fill",
            colorToRGB(((ColorAnimation) animation).getStartColor()),
            colorToRGB(((ColorAnimation) animation).getEndColor()),
            animation.getStartTime(),
            animation.getEndTime() - animation.getStartTime()));
        resetString.append(
            printAnimationHelper("fill", colorToRGB(((ColorAnimation) animation).getStartColor())));
        break;
      case SCALE:
        if (s.getType().equals(ShapeType.OVAL)) {
          attName1 = "rx";
          attName2 = "ry";
        } else if (s.getType().equals(ShapeType.RECTANGLE)) {
          attName1 = "width";
          attName2 = "height";
        }
        strBuild.append(printAnimationHelper(
            attName1,
            Integer.toString(Math.round(((WidthHeightDim) ((ScaleAnimation) animation)
                .getStartDimension()).getWidth())),
            Integer.toString(Math.round(((WidthHeightDim) ((ScaleAnimation) animation)
                .getEndDimension())
                .getWidth())),
            animation.getStartTime(),
            animation.getEndTime() - animation.getStartTime()));
        resetString.append(printAnimationHelper(attName1,
            Integer.toString(Math.round(((WidthHeightDim) ((ScaleAnimation) animation)
                .getStartDimension()).getWidth()))));

        strBuild.append(printAnimationHelper(
            attName2,
            Integer.toString(Math.round(((WidthHeightDim) ((ScaleAnimation) animation)
                .getStartDimension()).getHeight())),
            Integer.toString(Math.round(((WidthHeightDim) ((ScaleAnimation) animation)
                .getEndDimension())
                .getHeight())),
            animation.getStartTime(),
            animation.getEndTime() - animation.getStartTime()));
        resetString.append(printAnimationHelper(attName2, Integer.toString(Math.round((
            (WidthHeightDim) ((ScaleAnimation) animation)
                .getStartDimension()).getHeight()))));
        break;
      case CREATE:
        strBuild.append(printAnimationHelper("visibility", animation.getStartTime()));
        break;
      case DESTROY:
        strBuild.append(printAnimationHelper("visibility", animation.getEndTime()));
        break;
      default:
        throw new IOException("invalid type used");
    }
    return strBuild.toString();
  }


  private String colorToRGB(RGBColor startColor) {
    String red = Integer.toString(Math.round(startColor.getRed() * 255));
    String green = Integer.toString(Math.round(startColor.getGreen() * 255));
    String blue = Integer.toString(Math.round(startColor.getBlue() * 255));

    return "rgb(" + red + "," + green + "," + blue + ")";
  }

  /**
   * Finds the last animation in the animator to account for when to loopback.
   *
   * @param animation one animation to compare with current last end time
   */
  private void findLastAnimation(IAnimation animation) {
    if (animation.getEndTime() > lastAnimTime) {
      lastAnimTime = animation.getEndTime();
    }
  }

  /**
   * Returns a list of animation summaries of a given shape.
   *
   * @param shape the shape containing the animation summaries.
   * @return the list of summaries.
   */
  private List<AnimationSummary> getSummary(IAnimatedShape shape) throws IOException {
    checkNull(shape);

    List<AnimationSummary> animationSummaries = new ArrayList<>();
    Map<AnimationType, List<IAnimation>> animationMap = shape.getAnimations();
    AnimationSummary summary;

    for (List<IAnimation> aList : animationMap.values()) {
      for (IAnimation animation : aList) {
        findLastAnimation(animation);
        summary = new AnimationSummary(animation.getStartTime(),
            showAnimations(animation, shape),
            animation.getCreationIndex());
        animationSummaries.add(summary);
      }
    }

    return animationSummaries;
  }

  /**
   * Returns the string given an animation.
   *
   * @param startTick the start tick.
   * @param endTick the end tick.
   * @param startState the start state as a string.
   * @param endState the end state as a string.
   * @param attName attribute on which the animation occurs
   * @return a string representing the animation
   */
  private String printAnimationHelper(String attName, String startState, String
      endState, float startTick, float endTick) {

    StringBuilder builder = new StringBuilder();

    if (loopable) {
      builder.append("\n\t<animate attributeType=\"xml\" begin=\"base.begin+");
    } else {
      builder.append("\n\t<animate attributeType=\"xml\" begin=\"");
    }
    builder.append(tick2Time(startTick));
    builder.append("s\" dur=\"");
    builder.append(tick2Time(endTick));
    builder.append("s\" attributeName=\"");
    builder.append(attName);
    builder.append("\" from=\"");
    builder.append(startState);
    builder.append("\" to=\"");
    builder.append(endState);
    builder.append("\" fill=\"freeze\" />");

    return builder.toString();
  }


  /**
   * Returns the string given an animation to reset during looping.
   *
   * @param attName the attribute on which the animation occurs
   * @param startState value to reset animation to
   * @return a string representing the animation
   */
  private String printAnimationHelper(String attName, String startState) {

    StringBuilder builder = new StringBuilder();

    builder.append("\n\t<animate attributeType=\"xml\" begin=\"base.end");
    builder.append("\" dur=\"0.5ms\"");
    builder.append(" attributeName=\"");
    builder.append(attName);
    builder.append("\" to=\"");
    builder.append(startState);
    builder.append("\" fill=\"freeze\" />");

    return builder.toString();
  }

  /**
   * Returns the string given an animation.
   *
   * @param attName the attribute on which the animation occurs
   * @param tick the tick the action occurs
   * @return a string representing the animation
   */
  private String printAnimationHelper(String attName, float tick) {

    StringBuilder builder = new StringBuilder();

    if (loopable) {
      builder.append("\n\t<animate attributeType=\"xml\" begin=\"base.begin+");
    } else {
      builder.append("\n\t<animate attributeType=\"xml\" begin=\"");
    }
    builder.append(tick2Time(tick));
    builder.append("s\" dur=\"");
    builder.append(0);
    builder.append("s\" attributeName=\"");
    builder.append(attName);
    builder.append("\" fill=\"freeze\" />");

    return builder.toString();
  }

  /**
   * Returns the time for a corresponding tick.
   *
   * @param tick the tick.
   * @return the time
   */
  private float tick2Time(float tick) {
    return tick / speed;
  }

  /**
   * Determine if the position moves in either x-dir, y-dir, or both.
   *
   * @param start position from
   * @param end position dest
   * @return string x, y, or xy representing the direction of motion
   */
  private String typeOfMove(Position2D start, Position2D end) {
    String result = "";
    if (start.getX() != end.getX()) {
      result = result + "x";
    }
    if (start.getY() != end.getY()) {
      result = result + "y";
    }
    return result;
  }
}
