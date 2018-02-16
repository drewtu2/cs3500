import static org.junit.Assert.assertEquals;

import cs3500.hw03.inputscanner.IInputScanner;
import cs3500.hw03.inputscanner.InputScanner;
import java.io.StringReader;
import org.junit.Test;


public class InputScannerTest {

  @Test
  public void TestGetPile() {
    String myString = "C1";
    Readable myReadable = new StringReader(myString);
    IInputScanner myScanner = new InputScanner(myReadable);

    assertEquals(myString, myScanner.getPileToken());

  }

  @Test

  public void TestGetPile2ndInput() {
    String myString = "2 C1";
    Readable myReadable = new StringReader(myString);
    IInputScanner myScanner = new InputScanner(myReadable);

    assertEquals("C1", myScanner.getPileToken());
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
