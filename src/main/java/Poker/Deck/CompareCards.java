package Poker.Deck;

import java.util.Comparator;

public class CompareCards implements Comparator<Card>
{
   public CompareCards()
   {
   }

   @Override
   public int compare(Card card1, Card card2)
   {
      return card1.getRank().getVal() - card2.getRank().getVal();
   }

   public int compare2(Card card1, Card card2)
   {
      return card1.getRank().getVal() - card2.getRank().getVal();
   }
}