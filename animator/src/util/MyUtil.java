package util;

/**
 * General purpose utilities.
 */
public class MyUtil {

  /**
   * Performs a linear interpolation on the given values.
   *
   * @param startValue the start value
   * @param endValue the end value
   * @param startTime the start time
   * @param endTime the end time
   * @param time the time we're requesting
   * @return the value at the requested time
   */
  public static float interpolate(
      float startValue,
      float endValue,
      int startTime,
      int endTime,
      int time) {
    float componentA = startValue * (endTime - time) / (endTime - startTime);
    float componentB = endValue * (time - startTime) / (endTime - startTime);

    return componentA + componentB;
  }

  /**
   * Throws null pointer exception if the given input is null.
   *
   * @param input input to check
   */
  public static void checkNull(Object input) {
    if (input == null) {
      throw new NullPointerException("Input cannot be null");
    }
  }

}
