package cs3500.animator.model;

import cs3500.animator.animation.AnimationSummary;
import cs3500.animator.animation.IAnimation;
import cs3500.animator.shape.IAnimatedShape;
import cs3500.animator.shape.IShape;

import cs3500.animator.util.TweenModelBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A concrete implementation of an animator animator.model.
 */
public class AnimatorModel implements IAnimatorModel {

  protected Map<String, IAnimatedShape> shapes;

  /**
   * Static Builder class that constructs a model
   */
  public static final class Builder implements TweenModelBuilder<AnimatorModel> {

    @Override
    public TweenModelBuilder<AnimatorModel> addOval(String name, float cx, float cy, float xRadius,
        float yRadius, float red, float green, float blue, int startOfLife, int endOfLife) {
      //TODO implement this
      return null;
    }

    @Override
    public TweenModelBuilder<AnimatorModel> addRectangle(String name, float lx, float ly,
        float width,
        float height, float red, float green, float blue, int startOfLife, int endOfLife) {
      //TODO implement this
      return null;
    }

    @Override
    public TweenModelBuilder<AnimatorModel> addMove(String name, float moveFromX, float moveFromY,
        float moveToX, float moveToY, int startTime, int endTime) {
      //TODO implement this
      return null;
    }

    @Override
    public TweenModelBuilder<AnimatorModel> addColorChange(String name, float oldR, float oldG,
        float oldB, float newR, float newG, float newB, int startTime, int endTime) {
      //TODO implement this
      return null;
    }

    @Override
    public TweenModelBuilder<AnimatorModel> addScaleToChange(String name, float fromSx,
        float fromSy,
        float toSx, float toSy, int startTime, int endTime) {
      //TODO implement this
      return null;
    }

    @Override
    public AnimatorModel build() {
      //TODO implement this
      return null;
    }
  }

  /**
   * Constructs an Animator animator.model.
   */
  public AnimatorModel() {
    shapes = new HashMap<>();
  }

  @Override
  public void addShape(IAnimatedShape newShape) throws IllegalArgumentException {

    // Test to see if the shape exists
    if (shapes.containsKey(newShape.getName())) {
      throw new IllegalArgumentException("A shape by this name already exists!");
    }

    shapes.put(newShape.getName(), newShape);
  }

  @Override
  public void deleteShape(String shapeName) throws IllegalArgumentException {
    // Test to see if the shape exists
    if (!shapes.containsKey(shapeName)) {
      throw new IllegalArgumentException("This shape doesn't exist!");
    }

    shapes.remove(shapeName);

  }

  @Override
  public void addAnimation(String shapeName, IAnimation animation) throws IllegalArgumentException {
    // Test to see if the shape exists
    if (!shapes.containsKey(shapeName)) {
      throw new IllegalArgumentException("This shape doesn't exist!");
    }

    IAnimatedShape requestedShape = shapes.get(shapeName);

    // If the animation conflicts with another animation, an exception will be thrown and propagated
    // up.
    // TODO: make sure this updates the object in the map. Should work b/c java passes by reference
    requestedShape.addAnimation(animation);
  }

  @Override
  public String toString() {
    StringBuilder myStringBuilder = new StringBuilder();
    List<AnimationSummary> summaries = new ArrayList<>();

    myStringBuilder.append("Shapes:\n");

    // Extract a list of summaries
    for (IAnimatedShape animatedShape : shapes.values()) {
      myStringBuilder.append(animatedShape.toString());
      myStringBuilder.append("\n");
      summaries.addAll(animatedShape.getSummary());
    }

    myStringBuilder.append("Animations:\n");
    // Sort the summaries by start time
    Collections.sort(summaries);

    // Add the summaries to the string builder
    for (AnimationSummary summary : summaries) {
      myStringBuilder.append(summary.getDescription());
      myStringBuilder.append("\n");
    }

    return myStringBuilder.toString();
  }

  @Override
  public String toString(float time) {
    List<AnimationSummary> summaries = new ArrayList<>();

    StringBuilder myStringBuilder = new StringBuilder();

    // Extract a list of summaries
    for (IAnimatedShape animatedShape : shapes.values()) {
      myStringBuilder.append(animatedShape.stateAt(time).toString());
      summaries.addAll(animatedShape.getSummary());
    }

    myStringBuilder.append("Animations:\n");
    // Sort the summaries by start time
    Collections.sort(summaries);

    // Add the summaries to the string builder
    for (AnimationSummary summary : summaries) {
      myStringBuilder.append(summary.getDescription());
    }

    return myStringBuilder.toString();
  }

  @Override
  public List<IShape> getState(float time) {
    List<IShape> shapeList = new ArrayList<>();

    for (IAnimatedShape animatedShape : shapes.values()) {
      shapeList.add(animatedShape.stateAt(time));
    }

    return shapeList;
  }

  @Override
  public Set<String> listShapes() {
    return shapes.keySet();
  }
}
