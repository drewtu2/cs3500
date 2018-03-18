package cs3500.animator.shape;

import cs3500.animator.animation.AnimationSummary;
import cs3500.animator.animation.AnimationType;
import cs3500.animator.animation.IAnimation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Represents a shape that animations can be applied to. This can be thought of a shape that is a
 * function of time.
 */
public abstract class AbstractAnimatedShape extends AbstractShape implements IAnimatedShape {

  protected Map<AnimationType, List<IAnimation>> animationList;

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
      // If this animaiton overlaps
      if (myList.get(index).inBounds(addition.getStartTime())
          || myList.get(index).inBounds(addition.getEndTime())) {
        return false;
      }
    }

    return true;
  }

  @Override
  public IShape stateAt(float t) {
    //TODO see Interface for explanation
    throw new NotImplementedException();
  }

  @Override
  public List<AnimationSummary> getSummary() {
    AnimationSummary summary;

    List<AnimationSummary> los = new ArrayList<>();

    for (List<IAnimation> aList : animationList.values()) {
      for (IAnimation animation : aList) {
        summary = new AnimationSummary(animation.getStartTime(),
            animation.toString(this.getName()));
        los.add(summary);
      }
    }

    return los;
  }

  @Override
  public Map<AnimationType, List<IAnimation>> getAnimations() {
    return new HashMap<>(animationList);
  }
}
