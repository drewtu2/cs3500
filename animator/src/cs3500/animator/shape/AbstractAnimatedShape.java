package cs3500.animator.shape;

import cs3500.animator.animation.AnimationSummary;
import cs3500.animator.animation.AnimationType;
import cs3500.animator.animation.IAnimation;
import cs3500.animator.animation.IAnimationSummary;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a shape that animations can be applied to. This can be thought of a shape that is a
 * function of time.
 */
public abstract class AbstractAnimatedShape extends AbstractShape implements IAnimatedShape {

  protected Map<AnimationType, List<IAnimation>> animationList;
  protected int creationIndex;
  protected static int numCreated = 0;
  protected int layer;

  @Override
  public void addAnimation(IAnimation animation) throws IllegalArgumentException {

    if (!animationList.containsKey(animation.getType())) {
      animationList.put(animation.getType(), new ArrayList<>());
    }

    List<IAnimation> correspondingList = animationList.get(animation.getType());

    if (validAddition(correspondingList, animation)) {
      correspondingList.add(animation);
      Collections.sort(correspondingList);
    } else {
      throw new IllegalArgumentException("Could not add animation");
    }

  }

  /**
   * Returns true if the addition can be made. Addition cannot be made if another animaiton overlaps
   * with the addition.
   *
   * @param myList the list we're trying to add to
   * @param addition the item we're trying to add
   * @return true if the addition can be made
   */
  private boolean validAddition(List<IAnimation> myList, IAnimation addition) {

    for (int index = 0; index < myList.size(); ++index) {
      // If this anmiation overlaps
      if (myList.get(index).inBounds(addition.getStartTime())
          || myList.get(index).inBounds(addition.getEndTime())) {
        return false;
      }
    }

    return true;
  }

  @Override
  public IShape stateAt(int t) {

    if (t < 0) {
      throw new IllegalArgumentException("Cannot have negative value");
    }
    this.reset();

    // Iterate over every list of animations
    for (List<IAnimation> aList : animationList.values()) {
      // Iterate over every animation in the list
      for (IAnimation animation : aList) {
        // If the time we're searching for occurs after this animation,
        // apply this animation completely...
        if (t > animation.getEndTime()) {
          animation.setState(this, animation.getEndTime());
        } else if (animation.inBounds(t)) {
          // We're looking for a state part way through this animation...
          animation.setState(this, t);
          break;
        } else {
          // time we requested is BEFORE this animation started, don't do anything...
        }
      }
    }

    return this;
  }

  @Override
  public List<IAnimationSummary> getSummary() {
    IAnimationSummary summary;

    List<IAnimationSummary> los = new ArrayList<>();

    for (List<IAnimation> aList : animationList.values()) {
      for (IAnimation animation : aList) {
        summary = new AnimationSummary(animation.getStartTime(),
            animation.toString(this.getName()),
            animation.getCreationIndex());
        los.add(summary);
      }
    }

    return los;
  }

  @Override
  public Map<AnimationType, List<IAnimation>> getAnimations() {
    return new HashMap<>(animationList);
  }

  @Override
  public int getCreationIndex() {
    return creationIndex;
  }

  @Override
  public int getLayer() {
    return layer;
  }

  @Override
  public int compareTo(IAnimatedShape o) {
    if(this.layer == o.getLayer()) {
      return this.creationIndex - o.getCreationIndex();
    }
    return this.layer - o.getLayer();
  }
}
