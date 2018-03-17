package cs3500.animator.util;

public class myUtil {

  /**
   * Performs a linear interpolation on the given values.
   * @param startValue
   * @param endValue
   * @param startTime
   * @param endTime
   * @param time
   * @return
   */
  public static float interpolate(
      float startValue,
      float endValue,
      float startTime,
      float endTime,
      float time)
  {
    float componentA = startValue * (endTime - time)/(endTime - startTime);
    float componentB = endValue * (time - startTime)/(endTime - startTime);

    return componentA + componentB;
  }

  /**
   * Throws null pointer exception if the given input is null
   * @param input input to check
   */
  public static void checkNull(Object input) {
    if (input == null) {
      throw new NullPointerException("Input cannot be null");
    }
  }

}
