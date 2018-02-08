import java.io.StringReader;
import java.nio.CharBuffer;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cs3500.hw03.inputScanner.IInputScanner;
import cs3500.hw03.inputScanner.InputScanner;



public class InputScannerTest {

  @Test
  public void TestGetPile() {
    String myString = "C1";
    Readable myReadable = new StringReader(myString);
    IInputScanner myScanner = new InputScanner(myReadable);

    assertEquals(myScanner.getPileToken(), myString);

  }
  @Test

  public void TestGetPile2ndInput() {
    String myString = "2 C1";
    Readable myReadable = new StringReader(myString);
    IInputScanner myScanner = new InputScanner(myReadable);

    assertEquals(myScanner.getPileToken(), "C1");
  }

  @Test
  public void TestGetIndex() {
    String myString = "2";
    Readable myReadable = new StringReader(myString);
    IInputScanner myScanner = new InputScanner(myReadable);

    assertEquals(myScanner.getIndexToken(), myString);
  }

  @Test
  public void TestGetIndex3rdIndex() {
    String myString = "Rd C1 2";
    Readable myReadable = new StringReader(myString);
    IInputScanner myScanner = new InputScanner(myReadable);

    assertEquals(myScanner.getIndexToken(), "2");
  }

  @Test
  public void TestGetIndexQuit() {
    String myString = "Q C1 2";
    Readable myReadable = new StringReader(myString);
    IInputScanner myScanner = new InputScanner(myReadable);

    assertEquals(myScanner.getIndexToken(), "Q");
  }



}
