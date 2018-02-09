package cs3500.hw03.inputscanner;

public interface IInputScanner {

  /**
   * Continues reading from the input stream until a valid pile token can be returned. Returns the
   * valid pile token.
   *
   * @return a pile token
   */
  String getPileToken();

  /**
   * Continues reading from the input stream until a valid index token can be returned. Returns the
   * valid index token.
   *
   * @return an index token
   */
  String getIndexToken();
}
