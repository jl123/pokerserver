package Poker.Deck;

public interface Deck
{
   boolean isEmpty();

   int getSize();

   Card dealCard()throws EmptyDeckException;

   void burnCard() throws EmptyDeckException;

   void resetDeck();

   void shuffle();
}
