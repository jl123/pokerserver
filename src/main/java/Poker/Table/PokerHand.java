package Poker.Table;

import Poker.Deck.Card;

import java.util.ArrayList;
import java.util.List;

public class PokerHand
{
   private List<Card> holeCards;


   //default
   public PokerHand()
   {
      holeCards = new ArrayList<>();

   }

   //string to hand for eval purposes
   public PokerHand(String hand)
   {
      holeCards = new ArrayList<>();
      for (String s : hand.split(" "))
      {
         holeCards.add(new Card(s));
      }
   }

   public PokerHand(List<Card> cards)
   {
      holeCards = new ArrayList<>();
      holeCards.addAll(cards);
   }

   public void takeCard(Card card)
   {
      holeCards.add(card);
   }

   public List<Card> getHoleCards()
   {
      return holeCards;
   }


   @Override
   public String toString()
   {
      StringBuilder builder = new StringBuilder();
      holeCards.forEach(card ->{builder
            .append(card.toString()); builder.append(" ");});
      builder.append("\n");
      return builder.toString();
   }
}