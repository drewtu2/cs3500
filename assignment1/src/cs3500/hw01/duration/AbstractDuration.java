package cs3500.hw01.duration;

/**
 * Abstract base class for implementations of {@link Duration}.
 */
abstract class AbstractDuration implements Duration {

  /**
   * Constructs a {@link Duration} in a manner selected by concrete subclasses of this class.
   *
   * @param inSeconds the length in seconds
   * @return the new {@code Duration}
   */
  protected abstract Duration fromSeconds(long inSeconds);

  @Override
  public String format(String template) {

    // Handle the case where we receive an empty string
    if (template.length() <= 0) {
      throw new IllegalArgumentException();
    }

    String formattedString = "";
    char thisChar;
    for (int ii = 0; ii < template.length(); ++ii) {
      thisChar = template.charAt(ii);

      if (thisChar == '%') {
        if (ii == template.length() - 1) {
          // The "%" symbol was the last character in the list so it is a hanging
          // percent. Because we're running this check on it, the previous character
          // wasn't an escape... Throw IllegalArgumentException.
          throw new IllegalArgumentException();
        } else {
          formattedString += handleSpecifier(template.charAt(ii + 1));
          ++ii;
        }
      } else {
        formattedString += thisChar;
      }

    }
    return formattedString;
  }

  /**
   * Returns the appropriate string for a given specifier. Throws an IllegalArgumentException if the
   * given specifier is not in the following list
   *
   * @param specifier One of ['t', 'h', 'H', 'm', 'M', 's', 'S', '%']
   * @return The string corresponding to the specifier requested.
   * @throws IllegalArgumentException if specifier is not one of the given 8 characters
   */
  private String handleSpecifier(char specifier) {
    long inSeconds = this.inSeconds();
    switch (specifier) {
      case 't':
        return String.format("%d", inSeconds);
      case 'h':
        return String.format("%d", this.hoursOf(inSeconds));
      case 'H':
        return String.format("%02d", this.hoursOf(inSeconds));
      case 'm':
        return String.format("%d", this.minutesOf(inSeconds));
      case 'M':
        return String.format("%02d", this.minutesOf(inSeconds));
      case 's':
        return String.format("%d", this.secondsOf(inSeconds));
      case 'S':
        return String.format("%02d", this.secondsOf(inSeconds));
      case '%':
        return "%";
      default:
        throw new IllegalArgumentException();
    }
  }

  @Override
  public String toString() {
    return asHms();
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }

    if (!(that instanceof Duration)) {
      return false;
    }

    return ((Duration) that).inSeconds() == this.inSeconds();
  }

  @Override
  public int hashCode() {
    return Long.hashCode(inSeconds());
  }

  @Override
  public int compareTo(Duration that) {
    return Long.compare(this.inSeconds(), that.inSeconds());
  }

  @Override
  public Duration plus(Duration that) {
    return fromSeconds(this.inSeconds() + that.inSeconds());
  }

  /**
   * Converts an unpacked hours-minutes-seconds duration to its length in seconds.
   *
   * @param hours the number of hours
   * @param minutes the number of minutes
   * @param seconds the number of seconds
   * @return the duration in seconds
   */
  protected static long inSeconds(int hours, int minutes, int seconds) {
    return 3600 * hours + 60 * minutes + seconds;
  }

  /**
   * Formats an unpacked hours-minutes-seconds duration in the same {@code H:MM:SS} format that
   * {@link Duration#asHms()} returns. Assumes that
   *
   * @param hours the number of hours
   * @param minutes the number of minutes
   * @param seconds the number of seconds
   * @return formatted duration
   * @throws IllegalArgumentException if any argument is negative
   */
  protected static String asHms(int hours, int minutes, int seconds) {
    return String.format("%d:%02d:%02d", hours, minutes, seconds);
  }

  /**
   * Ensures that the hours, minutes, and seconds are all non-negative. Is factoring this out
   * overkill? Or should we also factor out the {@code inSeconds < 0} check in the two unary
   * constructors? Discuss.
   *
   * @param hours the number of hours
   * @param minutes the number of minutes
   * @param seconds the number of seconds
   * @throws IllegalArgumentException if any argument is negative
   */
  protected static void ensureHms(int hours, int minutes, int seconds) {
    if (hours < 0 || minutes < 0 || seconds < 0) {
      throw new IllegalArgumentException("must be non-negative");
    }
  }

  /**
   * Returns the number of whole hours in the given number of seconds.
   *
   * @param inSeconds the total number of seconds
   * @return the number of hours
   * @throws ArithmeticException if the correct result cannot fit in an {@code int}.
   */
  protected static int hoursOf(long inSeconds) {
    if (inSeconds / 3600 > Integer.MAX_VALUE) {
      throw new ArithmeticException("result cannot fit in type");
    }

    return (int) (inSeconds / 3600);
  }

  /**
   * Returns the number of whole minutes in the given number of seconds, less the number of whole
   * hours.
   *
   * @param inSeconds the total number of seconds
   * @return the number of remaining minutes
   */
  protected static int minutesOf(long inSeconds) {
    return (int) (inSeconds / 60 % 60);
  }

  /**
   * Returns the number of seconds remaining after all full minutes are removed from the given
   * number of seconds.
   *
   * @param inSeconds the total number of seconds
   * @return the number of remaining seconds
   */
  protected static int secondsOf(long inSeconds) {
    return (int) (inSeconds % 60);
  }
}
