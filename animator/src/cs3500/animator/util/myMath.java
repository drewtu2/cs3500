package cs3500.animator.util;

public class myMath {

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

}
