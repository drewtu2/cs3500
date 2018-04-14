package util;

import java.util.Map;

import cs3500.animator.shape.IAnimatedShape;

/**
 * represents general utility functions (all methods in class implementation should be static)
 */
public interface IMyUtil {

  /**
   * Throws null pointer exception if the given input is null.
   *
   * @param input input to check
   */
  void checkNull(Object input);

  /**
   * Creates a duplicate of the given shape map. The duplicated map only contains IShape Object (os
   * opposed to IAnimatedShapes because animations are time (aren't modified).
   *
   * @param current the given String->Animated Shape map.
   */
  Map<String, IAnimatedShape> duplicateMap(Map<String, IAnimatedShape> current);
}
