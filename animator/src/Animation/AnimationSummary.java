package animation;

/**
 * Represents a tuple of (start time, description) for animations that is sortable by start time.
 */
public class AnimationSummary implements Comparable<AnimationSummary> {

  protected final float time;
  protected final String description;

  /**
   * Constructs an animation summary.
   */
  public AnimationSummary(float time, String description) {
    this.time = time;
    this.description = description;
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
    return (int) (this.time - o.getTime());
  }
}
