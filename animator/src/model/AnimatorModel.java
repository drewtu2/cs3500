package model;

import animation.AnimationSummary;
import animation.IAnimation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import shape.IAnimatedShape;
import shape.IShape;

/**
 * A concrete implementation of an animator model.
 */
public class AnimatorModel implements IAnimatorModel {

  protected Map<String, IAnimatedShape> shapes;


  /**
   * Constructs an Animator model.
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
