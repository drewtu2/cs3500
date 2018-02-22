package Animation;

public class AnimationSummary implements Comparable<AnimationSummary>{
  protected float time;
  protected String description;

  /**
   * Constructs an animation summary.
   * @param time
   * @param description
   */
  public AnimationSummary(float time, String description) {
    this.time = time;
    this.description = description;
  }

  /**
   * Returns the time.
   * @return the time
   */
  float getTime() {

    return time;
  }

  /**
   * Returns the description.
   * @return the description
   */
  String getDescription() {

    return description;
  }

  @Override
  public int compareTo(AnimationSummary o) {
    return (int)(this.time - o.getTime());
  }
}
