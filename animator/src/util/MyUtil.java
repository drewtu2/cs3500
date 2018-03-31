package util;

import cs3500.animator.shape.IAnimatedShape;
import cs3500.animator.shape.ShapeFactory;
import java.util.HashMap;
import java.util.Map;

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

  /**
   * Creates a duplicate of the given shape map. The duplicated map only contains IShape Object (os
   * opposed to IAnimatedShapes because animations are time (aren't modified).
   *
   * @param current the given String->Animated Shape map.
   */
  public static Map<String, IAnimatedShape> duplicateMap(Map<String, IAnimatedShape> current) {
    Map<String, IAnimatedShape> newMap = new HashMap<>();

    for (String key : current.keySet()) {
      newMap.put(key, ShapeFactory.getShape(current.get(key)));
    }

    return newMap;
  }

}
