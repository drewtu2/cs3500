package cs3500.animator.model;

import static util.IUtil.duplicateMap;

import cs3500.animator.animation.AnimationFactory;
import cs3500.animator.animation.AnimationType;
import cs3500.animator.animation.IAnimation;
import cs3500.animator.animation.IAnimationSummary;
import cs3500.animator.shape.IAnimatedShape;
import cs3500.animator.shape.IRGBColor;
import cs3500.animator.shape.IShape;
import cs3500.animator.shape.Position2D;
import cs3500.animator.shape.RGBColor;
import cs3500.animator.shape.ShapeFactory;
import cs3500.animator.shape.dimension.IDimension;
import cs3500.animator.shape.dimension.WidthHeightDim;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import util.TweenModelBuilder;

/**
 * A concrete implementation of an animator animator.model.
 */
public class AnimatorModel implements IAnimatorModel, IModelView {

  /**
   * Static Builder class that constructs a model.
   */
  public static final class Builder implements TweenModelBuilder<AnimatorModel> {

    private Map<String, IAnimatedShape> shapes;
    private int endTick;

    /**
     * Default constructor for a builder.
     */
    public Builder() {
      shapes = new HashMap<>();
      endTick = 0;
    }

    @Override
    public TweenModelBuilder<AnimatorModel> addOval(String name, float cx, float cy, float xRadius,
        float yRadius, float red, float green, float blue, int startOfLife, int endOfLife) {
      // Creates a shape
      Position2D center = new Position2D(cx, cy);
      IRGBColor color = new RGBColor(red, green, blue);

      IAnimatedShape myOval = ShapeFactory.getOval(name, center, color, xRadius, yRadius);

      // Add appear animation to shape
      myOval.addAnimation(AnimationFactory.getAppearAnimation(startOfLife));
      myOval.addAnimation(AnimationFactory.getDisappearAnimation(endOfLife));

      if (endOfLife > endTick) {
        endTick = endOfLife;
      }

      // Adds shape to map
      shapes.put(name, myOval);

      return this;
    }

    @Override
    public TweenModelBuilder<AnimatorModel> addRectangle(String name, float lx, float ly,
        float width,
        float height, float red, float green, float blue, int startOfLife, int endOfLife) {
      // Creates a shape
      Position2D center = new Position2D(lx, ly);
      IRGBColor color = new RGBColor(red, green, blue);

      IAnimatedShape myRec = ShapeFactory.getRectangle(name, center, color, width, height);

      myRec.addAnimation(AnimationFactory.getAppearAnimation(startOfLife));
      myRec.addAnimation(AnimationFactory.getDisappearAnimation(endOfLife));

      if (endOfLife > endTick) {
        endTick = endOfLife;
      }

      // Adds shape to map
      shapes.put(name, myRec);
      // Add appear animation to shape
      // Add disappear animation to shape
      return this;
    }

    @Override
    public TweenModelBuilder<AnimatorModel> addMove(String name, float moveFromX, float moveFromY,
        float moveToX, float moveToY, int startTime, int endTime) {
      Position2D from = new Position2D(moveFromX, moveFromY);
      Position2D to = new Position2D(moveToX, moveToY);
      shapes.get(name).addAnimation(AnimationFactory.getMoveAnimation(from, to, startTime,
          endTime));
      // Add animation to shape
      return this;
    }

    @Override
    public TweenModelBuilder<AnimatorModel> addColorChange(String name, float oldR, float oldG,
        float oldB, float newR, float newG, float newB, int startTime, int endTime) {
      IRGBColor original = new RGBColor(oldR, oldG, oldB);
      IRGBColor newCol = new RGBColor(newR, newG, newB);
      shapes.get(name).addAnimation(AnimationFactory.getColorAnimation(original, newCol, startTime,
          endTime));
      // Add animation to shape
      return this;
    }

    @Override
    public TweenModelBuilder<AnimatorModel> addScaleToChange(String name, float fromSx,
        float fromSy,
        float toSx, float toSy, int startTime, int endTime) {
      IDimension startDim = new WidthHeightDim(fromSx, fromSy);
      IDimension endDim = new WidthHeightDim(toSx, toSy);
      shapes.get(name).addAnimation(AnimationFactory.getScaleAnimation(startDim,
          endDim, startTime, endTime));
      // Add animation to shape
      return this;
    }

    @Override
    public AnimatorModel build() {
      return new AnimatorModel(this);
    }
  }

  protected Map<String, IAnimatedShape> shapes;
  protected int animationEnd;

  /**
   * Default constructor of an Animator animator.model.
   */
  public AnimatorModel() {
    shapes = new HashMap<>();
  }

  /**
   * Constructs a model from a builder.
   *
   * @param b the builder
   */
  public AnimatorModel(Builder b) {
    shapes = new HashMap(b.shapes);
    animationEnd = b.endTick;

  }

  /**
   * Copy Constructor.
   *
   * @param model the model to copy.
   */
  public AnimatorModel(AnimatorModel model) {
    shapes = duplicateMap(model.getFullState());
    animationEnd = model.animationEnd;

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
    requestedShape.addAnimation(animation);

    // If this animation is a destroy animation, see if its the latest occurring destroy. The latest
    // occurring destroy. The latest destroy represents the end of the animation.
    if (animation.getType() == AnimationType.DESTROY) {
      if (animation.getEndTime() > animationEnd) {
        animationEnd = animation.getEndTime();
      }
    }
  }

  @Override
  public String toString() {
    StringBuilder myStringBuilder = new StringBuilder();
    List<IAnimationSummary> summaries = new ArrayList<>();

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
    for (IAnimationSummary summary : summaries) {
      myStringBuilder.append(summary.getDescription());
      myStringBuilder.append("\n");
    }

    return myStringBuilder.toString();
  }

  @Override
  public String toString(int time) {
    List<IAnimationSummary> summaries = new ArrayList<>();

    StringBuilder myStringBuilder = new StringBuilder();

    // Extract a list of summaries
    for (IAnimatedShape animatedShape : shapes.values()) {
      myStringBuilder.append(animatedShape.stateAt(time).toString());
      summaries.addAll(animatedShape.getSummary());
    }
    myStringBuilder.append("\n");
    myStringBuilder.append("Animations:\n");
    // Sort the summaries by start time
    Collections.sort(summaries);

    // Add the summaries to the string builder
    for (IAnimationSummary summary : summaries) {
      myStringBuilder.append(summary.getDescription());
      myStringBuilder.append("\n");
    }

    return myStringBuilder.toString();
  }

  @Override
  public Map<String, IAnimatedShape> getFullState() {
    return duplicateMap(shapes);
  }

  @Override
  public List<IShape> getState(int time) {
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

  @Override
  public int getEndTick() {
    return animationEnd;
  }

  @Override
  public IModelView getCopy() {
    return new AnimatorModel(this);
  }

  @Override
  public void addMap(Map<String, IAnimatedShape> fullState, Map<String, Boolean> shapeEnabled) {
    for (String name : fullState.keySet()) {
      if (shapeEnabled.get(name)) {
        shapes.put(name, ShapeFactory.getShape(fullState.get(name)));
      } else {
        shapes.remove(name);
      }
    }
  }
}
