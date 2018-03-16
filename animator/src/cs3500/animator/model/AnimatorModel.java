package cs3500.animator.model;

import cs3500.animator.animation.AnimationFactory;
import cs3500.animator.animation.AnimationSummary;
import cs3500.animator.animation.IAnimation;
import cs3500.animator.shape.IAnimatedShape;
import cs3500.animator.shape.IShape;
import cs3500.animator.shape.Position2D;
import cs3500.animator.shape.RGBColor;
import cs3500.animator.shape.ShapeFactory;
import cs3500.animator.shape.concrete.Oval;
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



  /**
   * Static Builder class that constructs a model
   */
  public static final class Builder implements TweenModelBuilder<AnimatorModel> {
    private Map<String, IAnimatedShape> shapes;

    /**
     * Default constructor for a builder
     */
    public Builder()
    {
      shapes = new HashMap<>();
    }

    @Override
    public TweenModelBuilder<AnimatorModel> addOval(String name, float cx, float cy, float xRadius,
        float yRadius, float red, float green, float blue, int startOfLife, int endOfLife) {
      // Creates a shape
      Position2D center = new Position2D(cx, cy);
      RGBColor color = new RGBColor(red, green, blue);

      IAnimatedShape myOval = ShapeFactory.getOval(name, center, color, xRadius, yRadius);

      // Add appear animation to shape
      myOval.addAnimation(AnimationFactory.getAppearAnimation(startOfLife));
      myOval.addAnimation(AnimationFactory.getDisappearAnimation(endOfLife));

      // Adds shape to map
      shapes.put(name, myOval);


      return this;
    }

    @Override
    public TweenModelBuilder<AnimatorModel> addRectangle(String name, float lx, float ly,
        float width,
        float height, float red, float green, float blue, int startOfLife, int endOfLife) {
      //TODO implement this

      // Creates a shape
      // Adds shape to map
      // Add appear animation to shape
      // Add disappear animation to shape
      AnimationFactory.getAppearAnimation(startOfLife);
      return this;
    }

    @Override
    public TweenModelBuilder<AnimatorModel> addMove(String name, float moveFromX, float moveFromY,
        float moveToX, float moveToY, int startTime, int endTime) {
      //TODO implement this
      Position2D from = new Position2D(moveFromX, moveFromY);
      Position2D to = new Position2D(moveToX, moveToY);
      AnimationFactory.getMoveAnimation(from, to, startTime, endTime);
      // Add animation to shape
      return this;
    }

    @Override
    public TweenModelBuilder<AnimatorModel> addColorChange(String name, float oldR, float oldG,
        float oldB, float newR, float newG, float newB, int startTime, int endTime) {
      //TODO implement this
      RGBColor original = new RGBColor(oldR, oldG, oldB);
      RGBColor newCol = new RGBColor(newR, newG, newB);
      AnimationFactory.getColorAnimation(original, newCol, startTime, endTime);
      // Add animation to shape
      return this;
    }

    @Override
    public TweenModelBuilder<AnimatorModel> addScaleToChange(String name, float fromSx,
        float fromSy,
        float toSx, float toSy, int startTime, int endTime) {
      //TODO implement this
      AnimationFactory.getScaleAnimation();
      // Add animation to shape
      return this;
    }

    @Override
    public AnimatorModel build() {
      //TODO implement this
      return new AnimatorModel(this);
    }
  }

  protected Map<String, IAnimatedShape> shapes;

  /**
   * Default constructor of an Animator animator.model.
   */
  public AnimatorModel() {
    shapes = new HashMap<>();
  }

  public AnimatorModel(Builder b) {
    shapes = new HashMap(b.shapes);

  }

  /**
   *
   * @param newShape the shape to add.
   * @throws IllegalArgumentException
   */

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
