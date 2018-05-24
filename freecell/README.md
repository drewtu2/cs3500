# Homework 4 Updates:
- made the member variables for piles and game state protected instead of private
- updated PileInterface with new method that allows the user to peek at the
top N cards of a pile. This allows the user to determine whether a move is valid
BEFORE attempting to move cards. 
- updated popCards in PileInterface to return a LIST of cards rather then just a 
single playing card. This makes more sense when we're removing a build rather than
removing only the top card. 
- added ValidAddition to the pileInterface so that users can check if a card can
be added onto another pile prior to attempting to do so. 

