import static org.junit.Assert.assertEquals;

import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;
import cs3500.hw02.PileType;
import cs3500.hw02.cards.CardValue;
import cs3500.hw02.cards.PlayingCard;
import cs3500.hw03.FreecellController;
import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class FreecellControllerTest {

  FreecellController myController;
  List<PlayingCard> myDeck;
  FreecellOperations myModel;
  Readable myRd;
  Appendable myAp;
  String start53 = "F1:\n"
      + "F2:\n"
      + "F3:\n"
      + "F4:\n"
      + "O1:\n"
      + "C1: A♠\n"
      + "C2: A♣\n"
      + "C3: A♥\n"
      + "C4: A♦\n"
      + "C5: 2♠\n"
      + "C6: 2♣\n"
      + "C7: 2♥\n"
      + "C8: 2♦\n"
      + "C9: 3♠\n"
      + "C10: 3♣\n"
      + "C11: 3♥\n"
      + "C12: 3♦\n"
      + "C13: 4♠\n"
      + "C14: 4♣\n"
      + "C15: 4♥\n"
      + "C16: 4♦\n"
      + "C17: 5♠\n"
      + "C18: 5♣\n"
      + "C19: 5♥\n"
      + "C20: 5♦\n"
      + "C21: 6♠\n"
      + "C22: 6♣\n"
      + "C23: 6♥\n"
      + "C24: 6♦\n"
      + "C25: 7♠\n"
      + "C26: 7♣\n"
      + "C27: 7♥\n"
      + "C28: 7♦\n"
      + "C29: 8♠\n"
      + "C30: 8♣\n"
      + "C31: 8♥\n"
      + "C32: 8♦\n"
      + "C33: 9♠\n"
      + "C34: 9♣\n"
      + "C35: 9♥\n"
      + "C36: 9♦\n"
      + "C37: 10♠\n"
      + "C38: 10♣\n"
      + "C39: 10♥\n"
      + "C40: 10♦\n"
      + "C41: J♠\n"
      + "C42: J♣\n"
      + "C43: J♥\n"
      + "C44: J♦\n"
      + "C45: Q♠\n"
      + "C46: Q♣\n"
      + "C47: Q♥\n"
      + "C48: Q♦\n"
      + "C49: K♠\n"
      + "C50: K♣\n"
      + "C51: K♥\n"
      + "C52: K♦\n"
      + "C53:";
  String c1ToC53 = "F1:\n"
      + "F2:\n"
      + "F3:\n"
      + "F4:\n"
      + "O1:\n"
      + "C1:\n"
      + "C2: A♣\n"
      + "C3: A♥\n"
      + "C4: A♦\n"
      + "C5: 2♠\n"
      + "C6: 2♣\n"
      + "C7: 2♥\n"
      + "C8: 2♦\n"
      + "C9: 3♠\n"
      + "C10: 3♣\n"
      + "C11: 3♥\n"
      + "C12: 3♦\n"
      + "C13: 4♠\n"
      + "C14: 4♣\n"
      + "C15: 4♥\n"
      + "C16: 4♦\n"
      + "C17: 5♠\n"
      + "C18: 5♣\n"
      + "C19: 5♥\n"
      + "C20: 5♦\n"
      + "C21: 6♠\n"
      + "C22: 6♣\n"
      + "C23: 6♥\n"
      + "C24: 6♦\n"
      + "C25: 7♠\n"
      + "C26: 7♣\n"
      + "C27: 7♥\n"
      + "C28: 7♦\n"
      + "C29: 8♠\n"
      + "C30: 8♣\n"
      + "C31: 8♥\n"
      + "C32: 8♦\n"
      + "C33: 9♠\n"
      + "C34: 9♣\n"
      + "C35: 9♥\n"
      + "C36: 9♦\n"
      + "C37: 10♠\n"
      + "C38: 10♣\n"
      + "C39: 10♥\n"
      + "C40: 10♦\n"
      + "C41: J♠\n"
      + "C42: J♣\n"
      + "C43: J♥\n"
      + "C44: J♦\n"
      + "C45: Q♠\n"
      + "C46: Q♣\n"
      + "C47: Q♥\n"
      + "C48: Q♦\n"
      + "C49: K♠\n"
      + "C50: K♣\n"
      + "C51: K♥\n"
      + "C52: K♦\n"
      + "C53: A♠";

  String invalidMove = "Invalid Move. Try Again. Bad Inputs.\n";

  /**
   * Preinitialize tests.
   */
  @Before
  public void initTests() {
    myModel = new FreecellModel();
    myDeck = myModel.getDeck();
    myRd = new StringReader("q");
    myAp = new StringBuffer("");

    myController = new FreecellController(myRd, myAp);

  }

  /* **************************************************************************
   * Test Creating Controller
   * ************************************************************************/

  /**
   * Test Null Deck.
   */
  @Test(expected = IllegalArgumentException.class)
  public void TestNullDeck() {
    try {
      myController.playGame(null, myModel, 4, 1, false);
    } catch (IOException e) {
      // do nothing.
    }
  }

  /**
   * Test Null Model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void TestNullModel() {
    try {
      myController.playGame(myDeck, null, 4, 1, false);
    } catch (IOException e) {
      // do nothing
    }
  }

  /**
   * Test Starting w/ invalid piles.
   */
  @Test
  public void TestInvalidStartPiles() {

    try {
      myController.playGame(myDeck, myModel, 0, 1, false);
    } catch (IOException e) {
      // do nothing...
    }

    assertEquals(myAp.toString(), "Could not start game.");

    try {
      myController.playGame(myDeck, myModel, 4, 0, false);
    } catch (IOException e) {
      // do nothing...
    }

    assertEquals(myAp.toString(), "Could not start game.Could not start game.");
  }

  /**
   * Test that game module prints the game state upon starting.
   */
  @Test
  public void TestGameStart() {
    try {
      myController.playGame(myDeck, myModel, 4, 1, false);
    } catch (IOException e) {
      // Do nothing
    }

    assertEquals(myModel.getGameState() + "\nGame quit prematurely.\n", myAp.toString());

  }

  /* **************************************************************************
   * Test Moves
   * ************************************************************************/

  /**
   * Tests what happens if an invalid character is given to the source pile.
   */
  @Test
  public void TestInvalidSourcePile() {
    myRd = new StringReader("R1 C1 1 C53 q");
    myAp = new StringBuffer("");

    myController = new FreecellController(myRd, myAp);

    try {
      myController.playGame(myDeck, myModel, 53, 1, false);
    } catch (IOException e) {
      // Do nothing
    }

    assertEquals(start53
            + "\n" + c1ToC53 + "\nGame quit prematurely.\n",
        myAp.toString());
  }

  /**
   * Tests what happens if an invalid character is given to the destination.
   */
  @Test
  public void TestInvalidDestinationPile() {
    myRd = new StringReader("C1 1 Cd C53 q");
    myAp = new StringBuffer("");

    myController = new FreecellController(myRd, myAp);

    try {
      myController.playGame(myDeck, myModel, 53, 1, false);
    } catch (IOException e) {
      // Do nothing
    }

    assertEquals(start53 + "\n" + c1ToC53 + "\nGame quit prematurely.\n", myAp.toString());
  }

  /**
   * Tests what happens if an invalid character is given to the card index.
   */
  @Test
  public void TestInvalidCardIndex() {
    myRd = new StringReader("C1 fd 1 C53 q");
    myAp = new StringBuffer("");

    myController = new FreecellController(myRd, myAp);

    try {
      myController.playGame(myDeck, myModel, 53, 1, false);
    } catch (IOException e) {
      // Do nothing
    }

    assertEquals(start53
            + "\n" + c1ToC53 + "\nGame quit prematurely.\n",
        myAp.toString());
  }

  /**
   * Tests if the program quits and sends quit message.
   */
  @Test
  public void TestQuit() {
    myRd = new StringReader("q C1 fd 1 C12");
    myAp = new StringBuffer("");

    myController = new FreecellController(myRd, myAp);

    try {
      myController.playGame(myDeck, myModel, 53, 1, false);
    } catch (IOException e) {
      // Do nothing
    }

    assertEquals(start53 + "\nGame quit prematurely.\n", myAp.toString());
  }

  /**
   * Tests if the program quits and sends quit message.
   */
  @Test
  public void TestQuitUpper() {
    myRd = new StringReader("Q C1 fd 1 C12");
    myAp = new StringBuffer("");

    myController = new FreecellController(myRd, myAp);

    try {
      myController.playGame(myDeck, myModel, 53, 1, false);
    } catch (IOException e) {
      // Do nothing
    }

    assertEquals(start53 + "\nGame quit prematurely.\n", myAp.toString());
  }

  /**
   * Tests if the program quits and sends quit message.
   */
  @Test
  public void TestQuitDestinationPile() {
    myRd = new StringReader("C1 fd 1 q");
    myAp = new StringBuffer("");

    myController = new FreecellController(myRd, myAp);

    try {
      myController.playGame(myDeck, myModel, 53, 1, false);
    } catch (IOException e) {
      // Do nothing
    }

    assertEquals(start53 + "\nGame quit prematurely.\n", myAp.toString());
  }

  /**
   * Tests if the program quits and sends quit message.
   */
  @Test
  public void TestQuitIndexToken() {
    myRd = new StringReader("C1 q fd 1 q");
    myAp = new StringBuffer("");

    myController = new FreecellController(myRd, myAp);

    try {
      myController.playGame(myDeck, myModel, 53, 1, false);
    } catch (IOException e) {
      // Do nothing
    }

    assertEquals(start53 + "\nGame quit prematurely.\n", myAp.toString());
  }

  /**
   * Tests the error thrown by the model is caught and handled.
   */
  @Test
  public void TestMoveBadCascadePile() {
    myRd = new StringReader("C1 1 C2 q");
    myAp = new StringBuffer("");

    myController = new FreecellController(myRd, myAp);

    try {
      myController.playGame(myDeck, myModel, 53, 1, false);
    } catch (IOException e) {
      // Do nothing
    }

    assertEquals(
        myModel.getGameState()
            + "\n" + invalidMove
            + myModel.getGameState() + "\n"
            + "Game quit prematurely.\n",
        myAp.toString());
  }

  /**
   * Tests the error thrown by the model is caught and handled.
   */
  @Test
  public void TestMoveMixedCascadePile() {
    myRd = new StringReader("C1s C1 1 C2 q");
    myAp = new StringBuffer("");

    myController = new FreecellController(myRd, myAp);

    try {
      myController.playGame(myDeck, myModel, 53, 1, false);
    } catch (IOException e) {
      // Do nothing
    }

    assertEquals(
        myModel.getGameState()
            + "\n" + invalidMove
            + myModel.getGameState() + "\n"
            + "Game quit prematurely.\n",
        myAp.toString());
  }

  ///**
  // * Tests the error thrown by the model is caught and handled.
  // */
  //@Test
  //public void TestMoveGameNotStarted() {
  //  // TODO: Write this. This is impossible to do through the controller.
  //}

  /**
   * Tests the game over message is played.
   */
  @Test
  public void TestWinning() {
    myRd = new StringReader(winGameSequence());
    myAp = new StringBuffer("");

    myController = new FreecellController(myRd, myAp);

    Collections.reverse(myDeck);
    try {
      myController.playGame(myDeck, myModel, 4, 1, false);
    } catch (IOException e) {
      // Do nothing
    }

    myModel = winGameModel();
    assertEquals(myAp.toString().contains("\nGame over.\n"), true);
  }

  /**
   * Returns a won model.
   *
   * @return a "won" model
   */
  public FreecellOperations winGameModel() {
    // Reversing the default deck should let us just move everything to foundation piles
    FreecellOperations model = new FreecellModel();
    List<PlayingCard> deck = model.getDeck();

    Collections.reverse(deck);
    model.startGame(deck, 4, 1, false);

    int counter = CardValue.values().length - 1;
    for (CardValue value : CardValue.values()) { // Iterate over all possible card types
      for (int pileNum = 0; pileNum < 4; ++pileNum) {
        model.move(PileType.CASCADE, pileNum, counter, PileType.FOUNDATION, pileNum);
      }
      --counter;
    }
    return model;
  }

  /**
   * Returns the sequence of moves to win the game. There need to be 4 cascade piles for this to
   * work.
   *
   * @return a sequence of strings representing winning the game.
   */
  public String winGameSequence() {
    // Reversing the default deck should let us just move everything to foundation piles
    FreecellOperations model = new FreecellModel();
    List<PlayingCard> deck = model.getDeck();

    StringBuilder winningString = new StringBuilder();

    Collections.reverse(deck);
    model.startGame(deck, 4, 1, false);

    int counter = CardValue.values().length - 1;
    for (CardValue value : CardValue.values()) { // Iterate over all possible card types
      for (int pileNum = 0; pileNum < 4; ++pileNum) {
        model.move(PileType.CASCADE, pileNum, counter, PileType.FOUNDATION, pileNum);
        winningString.append("C").append(pileNum + 1).append(" ");
        winningString.append(counter + 1).append(" ");
        winningString.append("F").append(pileNum + 1).append(" ");
      }
      --counter;
    }
    return winningString.toString();
  }

}
