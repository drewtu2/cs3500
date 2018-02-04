
import cs3500.hw03.FreecellController;

import cs3500.hw02.FreecellModel;
import cs3500.hw02.cards.PlayingCard;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FreecellControllerTest {

  FreecellController myController;
  List<PlayingCard> myDeck;
  FreecellModel myModel;
  Readable myRd;
  Appendable myAp;

  @Before
  public void initTests() {
    myController = new FreecellController(myRd, myAp);

  }

  @Test(expected = IllegalArgumentException.class)
  public void TestNullDeck() {
    try {
      myController.playGame(null, myModel, 4, 1, false);
    } catch (IOException e) {
     // do nothing.
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void TestNullModel() {
    try {
      myController.playGame(myDeck, null, 4, 1, false);
    } catch (IOException e) {
      // do nothing
    }
  }


}
