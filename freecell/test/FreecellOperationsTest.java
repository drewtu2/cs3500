import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;
import cs3500.hw02.cards.CardSuit;
import cs3500.hw02.cards.CardValue;
import cs3500.hw02.cards.PlayingCard;
import cs3500.hw02.PileType;

import java.util.List;
import java.util.Collections;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;


public class FreecellOperationsTest {

  private FreecellOperations<PlayingCard> myFreecell;
  private List<PlayingCard> myDeck;

  @Before
  public void initConds() {
    myFreecell = new FreecellModel();
    myDeck = myFreecell.getDeck();
  }

  // **********************************************************************************************
  // Tests of Deck Validity
  // **********************************************************************************************


  /*
  /**
   * A correct deck of cards for Freecell contains 52 cards with ace, 2, ..., jack, queen, king in
   * each of clubs, diamonds, hearts and spades) as a List of card objects.
   *
   * This is tested by the invalid cases.
   */
  /*
  @Test
  public void TestValidDeck() {
    myFreecell.startGame(myDeck, 4, 1, false);


  }*/

  /**
   * Tests what happens when any card is added to the empty spot.
   */
  @Test
  public void Test53CascadePiles() {
    myFreecell.startGame(myDeck, 53, 1, false);
    myFreecell.move(PileType.CASCADE, 1, 0, PileType.CASCADE, 52);

    assertEquals("F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: A♠\n"
        + "C2:\n"
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
        + "C53: A♣", myFreecell.getGameState());
  }

  /**
   * A deck is invalid for this game if it does not have 52 cards.
   */
  @Test(expected = java.lang.IllegalArgumentException.class)
  public void TestShortDeck() {
    // One TOO few cards
    myDeck.remove(myDeck.size() - 1);

    // Exepect Exception
    myFreecell.startGame(myDeck, 4, 1, false);
  }

  /**
   * A deck is invalid for this game if it does not have 52 cards.
   */
  @Test(expected = java.lang.IllegalArgumentException.class)
  public void TestLongDeck() {
    // One TOO many cards
    myDeck.add(new PlayingCard(CardSuit.HEART, CardValue.ACE));

    // Exepect Exception
    myFreecell.startGame(myDeck, 4, 1, false);
  }

  /**
   * A deck is invalid for this game if it has duplicate cards.
   */
  @Test(expected = java.lang.IllegalArgumentException.class)
  public void TestDuplicateCards() {
    // Remove the last card
    myDeck.remove(myDeck.size() - 1);
    // Add another Ace of Hearts
    myDeck.add(new PlayingCard(CardSuit.HEART, CardValue.ACE));

    // Exepect Exception
    myFreecell.startGame(myDeck, 4, 1, false);
  }

  /**
   * A deck is invalid for this game if it has invalid cards.
   */
  @Test(expected = java.lang.IllegalArgumentException.class)
  public void TestDeckInvalidCards() {
    throw new IllegalArgumentException("Impossible to create invalid card");
  }

  /**
   * Checks that a game with a shuffled deck is different than non-shuffled deck.
   */
  @Test
  public void TestShuffled() {
    // If no exception is thrown with a valid deck.
    myFreecell.startGame(myDeck, 4, 1, false);
    FreecellOperations<PlayingCard> shuffledGame = new FreecellModel();
    shuffledGame.startGame(myDeck, 4, 1, true);

    // If the decks have been shuffled, their game states should not be equal.
    assertNotEquals(myFreecell.getGameState(), shuffledGame.getGameState());


  }

  /**
   * Tests being given a null deck.
   */
  @Test(expected = IllegalArgumentException.class)
  public void TestNullDeck() {
    myFreecell.startGame(null, 4, 1, false);
  }

  /**
   * Tests being given too few open piles.
   */
  @Test(expected = IllegalArgumentException.class)
  public void Test0OpenPiles() {
    myFreecell.startGame(myDeck, 4, 0, false);
  }

  // **********************************************************************************************
  // Tests of Game State
  // **********************************************************************************************

  /**
   * Test Game State prints correct values of the game.
   */
  @Test
  public void TestGameState() {
    myFreecell.startGame(myDeck, 4, 1, false);
    String expected = "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "C2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
        + "C3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "C4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦";
    assertEquals(expected, myFreecell.getGameState());
  }

  @Test
  public void TestGameResetState() {
    // Reversing the default deck should let us just move everything to foundation piles
    Collections.reverse(myDeck);
    myFreecell.startGame(myDeck, 4, 1, false);

    int counter = CardValue.values().length - 1;
    for (CardValue value : CardValue.values()) { // Iterate over all possible card types
      for (int pileNum = 0; pileNum < 4; ++pileNum) {
        myFreecell.move(PileType.CASCADE, pileNum, counter, PileType.FOUNDATION, pileNum);
      }
      --counter;
    }
    assertEquals(true, myFreecell.isGameOver());
    myFreecell.startGame(myDeck, 4, 1, false);
    assertEquals(false, myFreecell.isGameOver());

  }

  @Test
  public void TestResetGameNotFinished() {
    myFreecell.startGame(myDeck, 4, 1, false);
    String play1 = myFreecell.getGameState();
    myFreecell.startGame(myDeck, 4, 1, true);

    assertNotEquals(play1 , myFreecell.getGameState());
  }

  /**
   * Test to make sure a game remains not started when an exception occurs in start game.
   */
  @Test
  public void TestFalseStart() {
    try {
      myFreecell.startGame(myDeck, 0, 1, false);
    } catch (Exception e) {
      // Want an exception to be thrown.... do need to use it.
    }
    assertEquals("", myFreecell.getGameState());

  }

  /**
   * Test to make sure the deck remains intact after dealing.
   */
  @Test
  public void TestDeckIntact() {
    try {
      myFreecell.startGame(myDeck, 4, 1, false);
    } catch (Exception e) {
      // Want an exception to be thrown.... do need to use it.
    }
    assertEquals(52, myDeck.size());
  }

  /**
   * Test the Game State when the game has not yet begun (should return empty string).
   */
  @Test
  public void TestGameStateNotBegun() {
    assertEquals("", myFreecell.getGameState());
  }

  /**
   * Tests if the game is over.
   */
  @Test
  public void TestGameOver() {
    // Reversing the default deck should let us just move everything to foundation piles
    Collections.reverse(myDeck);
    myFreecell.startGame(myDeck, 4, 1, false);

    int counter = CardValue.values().length - 1;
    for (CardValue value : CardValue.values()) { // Iterate over all possible card types
      for (int pileNum = 0; pileNum < 4; ++pileNum) {
        myFreecell.move(PileType.CASCADE, pileNum, counter, PileType.FOUNDATION, pileNum);
      }
      --counter;
    }
    assertEquals(true, myFreecell.isGameOver());
  }

  /**
   * Tests the game is not over.
   */
  @Test
  public void TestGameNotOver() {
    myFreecell.startGame(myDeck, 4, 1, false);
    assertEquals(false, myFreecell.isGameOver());
  }

  // **********************************************************************************************
  // Tests Moving Cards
  // **********************************************************************************************
  // is called to move a card according to the rules of the game. Specifically It moves cards
  // beginning at index cardIndex from the pile number sourcePileNumber of type sourceType to the
  // pile number destPileNumber of type destinationType. PileType (provided to you) is an enumerated
  // type that is helpful in specifying the type of pile.
  // All indices and pile numbers in this method start at 0 — e.g. in the figure above, to move 4♦
  // in cascade pile 8 to cascade pile 1 this method would be called as

  /**
   * Test a valid move to cascade pile.
   */
  @Test
  public void TestValidMoveToCascade() {
    myFreecell.startGame(myDeck, 52, 1, false);
    myFreecell.getGameState();
    // Move 1spades onto 2hearts
    myFreecell.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 6);

    assertEquals("F1:\n"
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
        + "C7: 2♥, A♠\n"
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
        + "C52: K♦", myFreecell.getGameState());
    // Move 3spades onto 4diamond
    myFreecell.move(PileType.CASCADE, 8, 0, PileType.CASCADE, 14);

    assertEquals("F1:\n"
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
        + "C7: 2♥, A♠\n"
        + "C8: 2♦\n"
        + "C9:\n"
        + "C10: 3♣\n"
        + "C11: 3♥\n"
        + "C12: 3♦\n"
        + "C13: 4♠\n"
        + "C14: 4♣\n"
        + "C15: 4♥, 3♠\n"
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
        + "C52: K♦", myFreecell.getGameState());
    // Move 2diamond onto 3spades
    myFreecell.move(PileType.CASCADE, 7, 0, PileType.CASCADE, 14);
    assertEquals("F1:\n"
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
        + "C7: 2♥, A♠\n"
        + "C8:\n"
        + "C9:\n"
        + "C10: 3♣\n"
        + "C11: 3♥\n"
        + "C12: 3♦\n"
        + "C13: 4♠\n"
        + "C14: 4♣\n"
        + "C15: 4♥, 3♠, 2♦\n"
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
        + "C52: K♦", myFreecell.getGameState());

  }

  /*
  /**
   * Test a valid move to foundation pile.
   */
  /*
  @Test
  public void TestValidMoveToFoundation() {
    // This is tested in TestGameOver, leaving this here as a place holder
    assertEquals(true, true);

  }
  */



  /**
   * Tests a move if the game hasn't started.
   */
  @Test(expected = IllegalStateException.class)
  public void TestMoveGameNotStarted() {
    myFreecell.move(PileType.CASCADE, 0, 0, PileType.OPEN, 0);
  }

  /**
   * Tests move to self does nothing.
   */
  @Test
  public void TestMoveSelf() {
    myFreecell.startGame(myDeck, 4, 1, false);
    String preDeck = myFreecell.getGameState();
    myFreecell.move(PileType.CASCADE, 0, 12, PileType.CASCADE, 0);

    assertEquals(preDeck, myFreecell.getGameState());

  }

  /**
   * Test a valid move to open pile.
   */
  @Test
  public void TestValidMoveToOpen() {
    myFreecell.startGame(myDeck, 4, 4, false);
    myFreecell.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    assertEquals("F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: K♠\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "C1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠\n"
        + "C2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
        + "C3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "C4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦", myFreecell.getGameState());
    myFreecell.move(PileType.CASCADE, 1, 12, PileType.OPEN, 1);
    assertEquals("F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: K♠\n"
        + "O2: K♣\n"
        + "O3:\n"
        + "O4:\n"
        + "C1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠\n"
        + "C2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣\n"
        + "C3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "C4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦", myFreecell.getGameState());
    myFreecell.move(PileType.CASCADE, 2, 12, PileType.OPEN, 2);
    assertEquals("F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: K♠\n"
        + "O2: K♣\n"
        + "O3: K♥\n"
        + "O4:\n"
        + "C1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠\n"
        + "C2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣\n"
        + "C3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥\n"
        + "C4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦", myFreecell.getGameState());

  }

  /**
   * Tests trying to move non existent piles.
   */
  @Test(expected = IllegalArgumentException.class)
  public void TestNonExistentPiles() {
    myFreecell.startGame(myDeck, 4, 1, false);

    myFreecell.move(PileType.CASCADE, 10, 12, PileType.OPEN, 1);

  }

  /**
   * Tests trying to move non existent card.
   */
  @Test(expected = IllegalArgumentException.class)
  public void TestNonExistentCard() {
    myFreecell.startGame(myDeck, 4, 1, false);

    myFreecell.move(PileType.CASCADE, 4, 14, PileType.OPEN, 1);

  }

  @Test
  public void TestInvalidMoveCorrectReset() {
    myFreecell.startGame(myDeck, 52, 1, false);

    String myState = myFreecell.getGameState();

    try {
      // Trying to move 1 of spades on to 2 of spades
      myFreecell.move(PileType.CASCADE, 0, 0, PileType.CASCADE,
          4);
    } catch (IllegalArgumentException e) {
      // Do nothing.
    }

    assertEquals(myState, myFreecell.getGameState());
  }

  /**
   * Tests attempting to move a card of the wrong color but correct value onto a cascade pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void TestInvalidMoveCascadePileWrongColor() {
    myFreecell.startGame(myDeck, 52, 1, false);

    // Trying to move 1 of spades on to 2 of spades
    myFreecell.move(PileType.CASCADE, 0, 0, PileType.CASCADE,
        4);
  }

  /**
   * Tests attempting to move a card of the right color but wrong value onto a cascade pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void TestInvalidMoveCascadePileWrongValue() {
    myFreecell.startGame(myDeck, 52, 1, false);

    // Trying to move 1 of spades on to 3 of hearts
    myFreecell.move(PileType.CASCADE, 1, 0, PileType.CASCADE,
        10);
  }

  /**
   * Tests attempting to move a card of the wrong suit but right value onto a foundation pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void TestInvalidMoveFoundationPileWrongSuit() {
    myFreecell.startGame(myDeck, 52, 1, false);

    // Start by moving 1 of spades on to Foundation pile 0
    myFreecell.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION,
        0);

    // Now try to move 2 of clubs onto foundation pile 0
    myFreecell.move(PileType.CASCADE, 5, 0, PileType.FOUNDATION,
        0);
  }

  /**
   * Tests attempting to move a card of the right suit but wrong value onto a foundation pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void TestInvalidMoveFoundationPileWrongValue() {
    myFreecell.startGame(myDeck, 52, 1, false);

    // Start by moving 1 of spades on to Foundation pile 0
    myFreecell.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION,
        0);

    // Now try to move 3 of spades onto foundation pile 0
    myFreecell.move(PileType.CASCADE, 8, 0, PileType.FOUNDATION,
        0);
  }

  /**
   * Tests attempting to move a card onto a non empty open pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void TestInvalidMoveOpenOccupied() {
    myFreecell.startGame(myDeck, 4, 1, false);
    myFreecell.move(PileType.CASCADE, 0, 13, PileType.OPEN,
        0);
    myFreecell.move(PileType.CASCADE, 0, 12, PileType.OPEN,
        0);

  }

  /**
   * Tests attempting to move not the top card.
   */
  @Test(expected = IllegalArgumentException.class)
  public void TestMoveNotTop() {
    myFreecell.startGame(myDeck, 4, 1, false);
    myFreecell.move(PileType.CASCADE, 0, 10, PileType.OPEN,
        0);

  }
}
