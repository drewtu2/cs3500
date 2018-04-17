package cs3500.animator.provider.object;

/**
 * Represents a generic canvas object that can be laid on a canvas at a certain time period.
 */
public abstract class AbstractCanvasObject implements ICanvasObject {

  public static final String ERROR_END_TIME_BEFORE_START_TIME =
      "The start time must come before the end time.";
  public static final String ERROR_START_TIME_NEGATIVE =
      "The start time must be non-negative.";

  private int startTime;
  private int endTime;

  /**
   * Constructs a canvas object with the given start and end times.
   *
   * @param startTime the time to show the object
   * @param endTime the time to hide the object
   * @throws IllegalArgumentException if the total duration of the object is 0 or less or if the
   * start time is negative
   */
  public AbstractCanvasObject(int startTime, int endTime) throws IllegalArgumentException {
    if (endTime <= startTime) {
      throw new IllegalArgumentException(ERROR_END_TIME_BEFORE_START_TIME);
    }
    if (startTime < 0) {
      throw new IllegalArgumentException(ERROR_START_TIME_NEGATIVE);
    }

    this.startTime = startTime;
    this.endTime = endTime;
  }

  /**
   * A getter for the start time.
   *
   * @return the start time value
   */
  public int getStartTime() {
    return startTime;
  }

  /**
   * A getter for the end time.
   *
   * @return the end time value
   */
  public int getEndTime() {
    return endTime;
  }

  @Override
  public int compareTo(ICanvasObject other) {
    // If this object starts before the other object, this object is less than the other object
    // If both objects have the same start time, compare this object's end time with the other object
    if (startTime == ((AbstractCanvasObject) other).startTime) {
      return endTime - ((AbstractCanvasObject) other).endTime;
    }

    return startTime - ((AbstractCanvasObject) other).startTime;
  }
}