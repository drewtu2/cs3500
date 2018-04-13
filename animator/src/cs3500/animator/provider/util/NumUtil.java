package cs3500.animator.provider.util;

/**
 * Utility class
 */
public class NumUtil {

  /**
   * Rounds a number to its integer value.
   * @param num
   * @return rounded int of number
   */
  public static int round(float num) {
    return Math.round(num);
  }

  /**
   * Rounds down to the nearest integer value.
   * @param num the number.
   * @return the rounded int.
   */
  public static int round(double num) {
    return (int)(num);
  }
}
