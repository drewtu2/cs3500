package cs3500.hw03.inputScanner;

import java.util.Scanner;

/**
 * At any time, if the arguments are not valid, input will be re-requested.
 * Invalid inputs include something other than
 * - 'q' or 'Q' to quit the game;
 * - a letter other than 'C', 'F', 'O' to name a pile;
 * - anything that cannot be parsed to a valid number after the pile letter;
 * - anything that is not a number for the card index.
 */
public class InputScanner implements IInputScanner{

  private static Scanner inputScanner;

  public InputScanner(Readable inputStream) {
    inputScanner = new Scanner(inputStream);
  }

  @Override
  public String getPileToken() {
    String token;
    do {
      token = inputScanner.next();
    } while (!validPile(token));

    return token;
  }

  @Override
  public String getIndexToken() {
    String token;
    do {
      token = inputScanner.next();
    } while (!validIndex(token));

    return token;
  }

  /**
   * Returns a boolean if the given token represents a pile or quit.
   * - 'q' or 'Q' to quit the game;
   * - a letter other than 'C', 'F', 'O' to name a pile;
   * - anything that cannot be parsed to a valid number after the pile letter;
   * @param token the token to evaluate
   * @return true if the token is a valid pile
   */
  private boolean validPile(String token) {

    if (token == null) {
      return false;
    }

    String indicator = token.substring(0, 1).toLowerCase();
    String index = token.substring(1);

    if(token.equals("q")) {
      return true;
    }

    if (!indicator.equals("f") && !indicator.equals("o") && !indicator.equals("c")) {
      return false;
    }

    if (!validIndex(index)) {

      return false;
    }

    return true;
  }

  /**
   * Returns a boolean if the given token represents an index or quit.
   * - 'q' or 'Q' to quit the game;
   * - anything that is not a number for the card index.
   * @param token the token to evaluate
   * @return true if the token is a valid index.
   */
  private boolean validIndex(String token) {
    return token != null && (isNumeric(token) || token.toLowerCase().equals("q"));
  }

  /**
   * Returns true if the given string is entirely an integer
   * @param token
   * @return true if the string is an integer
   */
  private boolean isNumeric(String token) {
    return token != null && token.matches("[0-9]+");
  }

}
