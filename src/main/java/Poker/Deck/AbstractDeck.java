package Poker.Deck;

import java.util.*;

abstract class AbstractDeck implements Deck
{
   private Stack<Card> theDeck;
   private List<Card> dealtCards;


   public boolean isEmpty() { return theDeck.isEmpty(); }

   public Card dealCard() throws EmptyDeckException
   {
      if ( getSize() == 0 )
      {
         throw new EmptyDeckException();
      }
      dealtCards.add(theDeck.peek());
      return new Card(theDeck.pop());
   }

   public void burnCard() throws EmptyDeckException
   {
      if ( getSize() == 0 )
      {
         throw new EmptyDeckException();
      }
      dealtCards.add(theDeck.pop());
   }


   public void resetDeck(){
      dealtCards.forEach(theDeck::push);
      dealtCards = new ArrayList<>();
      shuffle();
   }


   public int getSize() { return theDeck.size(); }


   public void shuffle(){ Collections.shuffle(theDeck); }

   private void cut()
   {

   }

   void initialize(int LOWEST_CARD_VAL)
   {
      theDeck = new Stack<>();
      dealtCards = new ArrayList<>();
      Arrays.stream(Card.Suit.values()).forEach(suit ->
            Arrays.stream(Card.Rank.values())
                  .filter(r -> r.getVal() >= LOWEST_CARD_VAL)
                  .forEach(rank -> theDeck.push(new Card(rank, suit))));
   }
}
