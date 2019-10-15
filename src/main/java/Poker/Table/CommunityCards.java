package Poker.Table;



import Poker.Deck.Card;

import java.util.ArrayList;
import java.util.List;

public class CommunityCards
{
   private List<Card> cards;

   public CommunityCards()
   {
      cards = new ArrayList<>();
   }

   public void takeCards(Card card)
   {
      cards.add(card);
   }

   public List<Card> getCards()
   {
      return new ArrayList<>(cards);
   }

   public int getNumCards()
   {
      return cards.size();
   }
   @Override
   public String toString()
   {
      StringBuilder builder = new StringBuilder();
      cards.forEach(card -> {
         builder.append(card);
         builder.append(" ");
      });
      return builder.toString();
   }
}
