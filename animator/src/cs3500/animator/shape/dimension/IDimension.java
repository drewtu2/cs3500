package cs3500.animator.shape.dimension;

/**
 * Describes a dimension.
 */
public interface IDimension {

  /**
   * Returns the string representation of this dimension.
   */
  String toString();

  /**
   * Assuming this is the starting dimension, return the dimension between this dimension and a
   * requested dimension with repsect to time.
   *
   * @param end the end state we want to reach
   * @param startTime the start time we have for the transition
   * @param endTime the end time we have for the transition
   * @param time the time we're looking at
   * @return the dimension at that time
   * @throws IllegalArgumentException if the time is outside the duration or is not of the correct
   *  IDimension
   */
  IDimension getIntermediate(IDimension end, int startTime, int endTime, int time)
      throws IllegalArgumentException;

  /**
   * Create copy of this IDimension.
   *
   * @return copy of this IDimension
   */
  IDimension getCopy();
}

