import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;
import cs3500.hw02.PileType;
import cs3500.hw02.cards.CardValue;
import cs3500.hw02.cards.PlayingCard;
import cs3500.hw04.FreecellModelCreator;
import cs3500.hw04.FreecellModelCreator.GameType;
import cs3500.hw04.FreecellModelMulti;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class FreecellMultiTest {

  FreecellOperations<PlayingCard> myMulti;

  @Before
  public void setUp() {
    myMulti = FreecellModelCreator.create(GameType.MULTIMOVE);
  }

  @Test
  public void TestFactory() {
    assertThat(myMulti, instanceOf(FreecellModelMulti.class));

    FreecellOperations singleFromFactory = FreecellModelCreator.create(GameType.SINGLEMOVE);
    assertThat(singleFromFactory, instanceOf(FreecellModel.class));
  }

  @Test
  public void TestValidMultiMove() {
    List<PlayingCard> myDeck = myMulti.getDeck();
    myMulti.startGame(myDeck, 52, 8, false);
    // Create a stack of level 2
    myMulti.move(PileType.CASCADE, 1, 0, PileType.CASCADE, 7);
    // Create move stack of level 2 to another cascade.
    myMulti.move(PileType.CASCADE, 7, 0, PileType.CASCADE, 8);
    myMulti.move(PileType.CASCADE, 8, 0, PileType.CASCADE, 15);
    myMulti.move(PileType.CASCADE, 15, 0, PileType.CASCADE, 17);

    assertEquals("F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "O6:\n"
        + "O7:\n"
        + "O8:\n"
        + "C1: A♠\n"
        + "C2:\n"
        + "C3: A♥\n"
        + "C4: A♦\n"
        + "C5: 2♠\n"
        + "C6: 2♣\n"
        + "C7: 2♥\n"
        + "C8:\n"
        + "C9:\n"
        + "C10: 3♣\n"
        + "C11: 3♥\n"
        + "C12: 3♦\n"
        + "C13: 4♠\n"
        + "C14: 4♣\n"
        + "C15: 4♥\n"
        + "C16:\n"
        + "C17: 5♠\n"
        + "C18: 5♣, 4♦, 3♠, 2♦, A♣\n"
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
        + "C52: K♦", myMulti.getGameState());
  }

  @Test
  public void TestGameOver() {
    List myDeck = myMulti.getDeck();
    // Reversing the default deck should let us just move everything to foundation piles
    Collections.reverse(myDeck);
    myMulti.startGame(myDeck, 4, 1, false);

    int counter = CardValue.values().length - 1;
    for (CardValue value : CardValue.values()) { // Iterate over all possible card types
      for (int pileNum = 0; pileNum < 4; ++pileNum) {
        myMulti.move(PileType.CASCADE, pileNum, counter, PileType.FOUNDATION, pileNum);
      }
      --counter;
    }
    assertEquals(true, myMulti.isGameOver());
  }

  @Test(expected = IllegalArgumentException.class)
  public void TestInvalidDest() {
    List<PlayingCard> myDeck = myMulti.getDeck();
    myMulti.startGame(myDeck, 52, 4, false);
    // Create a stack of level 2
    myMulti.move(PileType.CASCADE, 1, 0, PileType.CASCADE, 7);
    // Create move stack of level 2 to another cascade.
    myMulti.move(PileType.CASCADE, 7, 0, PileType.FOUNDATION, 8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void TestInvalidCardIndexNegative() {
    List<PlayingCard> myDeck = myMulti.getDeck();
    myMulti.startGame(myDeck, 52, 4, false);
    // Create a stack of level 2
    myMulti.move(PileType.CASCADE, 1, -1, PileType.CASCADE, 7);

  }

  @Test(expected = IllegalArgumentException.class)
  public void TestInvalidCardIndexGreaterThanDeckSize() {
    List<PlayingCard> myDeck = myMulti.getDeck();
    myMulti.startGame(myDeck, 52, 4, false);
    // Create a stack of level 2
    myMulti.move(PileType.CASCADE, 1, 2, PileType.CASCADE, 7);

  }

  @Test(expected = IllegalArgumentException.class)
  public void TestInvalidBuild() {
    List<PlayingCard> myDeck = myMulti.getDeck();
    myMulti.startGame(myDeck, 4, 4, false);
    // Create a stack of level 2
    myMulti.move(PileType.CASCADE, 1, 0, PileType.CASCADE, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void TestMoveToBadBuild() {
    List<PlayingCard> myDeck = myMulti.getDeck();
    myMulti.startGame(myDeck, 52, 4, false);
    // Create a stack of level 2
    myMulti.move(PileType.CASCADE, 1, 0, PileType.CASCADE, 7);
    // Create move stack of level 2 to another cascade.
    myMulti.move(PileType.CASCADE, 7, 0, PileType.CASCADE, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void TestNotEnoughSpots() {
    List<PlayingCard> myDeck = myMulti.getDeck();
    myMulti.startGame(myDeck, 26, 1, false);
    myMulti.move(PileType.CASCADE, 0, 1, PileType.CASCADE, 2);
    myMulti.move(PileType.CASCADE, 2, 1, PileType.CASCADE, 8);
    myMulti.move(PileType.CASCADE, 8, 1, PileType.CASCADE, 10);
  }

  @Test(expected = IllegalStateException.class)
  public void TestGameNotStarted() {
    myMulti.move(PileType.CASCADE, 1, 0, PileType.CASCADE, 7);
  }

}
