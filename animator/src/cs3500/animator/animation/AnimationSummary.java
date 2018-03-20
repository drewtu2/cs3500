package cs3500.animator.animation;

/**
 * Represents a tuple of (start time, description) for animations that is sortable by start time.
 */
public class AnimationSummary implements Comparable<AnimationSummary> {

  protected final float time;
  protected final int creationIndex;
  protected final String description;

  /**
   * Constructs an animation summary.
   */
  public AnimationSummary(float time, String description, int creationIndex) {
    this.time = time;
    this.description = description;
    this.creationIndex = creationIndex;
  }

  /**
   * Returns the time.
   *
   * @return the time
   */
  public float getTime() {

    return time;
  }

  /**
   * Returns the description.
   *
   * @return the description
   */
  public String getDescription() {

    return description;
  }

  @Override
  public int compareTo(AnimationSummary o) {
    int startTime = (int) (this.time - o.getTime());
    if (startTime == 0) {
      return this.creationIndex - o.creationIndex;
    } else {
      return startTime;
    }
  }
}
