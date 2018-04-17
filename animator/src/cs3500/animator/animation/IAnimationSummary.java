package cs3500.animator.animation;

/**
 * Represents a tuple of (start time, description) for animations that is sortable by start time.
 */
public interface IAnimationSummary extends Comparable<IAnimationSummary> {

  /**
   * Returns the time this animation starts at. Animations are sorted by this time.
   *
   * @return the time
   */
  float getTime();

  /**
   * Returns the description of this animation.
   *
   * @return the description
   */
  String getDescription();

  /**
   * Returns the creation index of this animation (0 means first).
   *
   * @return the creation index.
   */
  int getCreationIndex();

}
