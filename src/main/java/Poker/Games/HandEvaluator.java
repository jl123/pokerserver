package Poker.Games;

import Poker.Deck.Card;
import Poker.Deck.CompareCards;
import Poker.Table.CommunityCards;
import Poker.Table.PokerHand;

import java.util.*;
import java.util.stream.Collectors;


public enum HandEvaluator
{
   HIGH_CARD, PAIR, TWO_PAIR, THREE_KIND, STRAIGHT, FLUSH, FULL_HOUSE, QUADS, STRAIGHT_FLUSH;

   public static  HandRank getBestHand(PokerHand hand, CommunityCards commCards)
   {

      List<PokerHand> candidates = new ArrayList<>();
      if (hand.getHoleCards().size() < 5)
      {
         candidates = getFiveCardHands(hand, commCards);
      }
      else if (hand.getHoleCards().size() == 5)
      {
         candidates.add(hand);
      }
      candidates.sort(new CompareHands().reversed());
      return getHandRank(candidates.get(0));
   }

   private static List<PokerHand> getFiveCardHands(PokerHand hand, CommunityCards commCards)
   {
      List<Card> sortedHand = new ArrayList<>();
      sortedHand.addAll(hand.getHoleCards());
      sortedHand.addAll(commCards.getCards());
      sortedHand.sort(new CompareCards());
      List<PokerHand> candidates = new ArrayList<>();
      for (int i = 0; i < sortedHand.size() - 1; i++)
      {
         for (int j = i + 1; j < sortedHand.size(); j++)
         {
            List<Card> exclude = new ArrayList<>();
            exclude.add(sortedHand.get(i));
            exclude.add(sortedHand.get(j));
            candidates.add(new PokerHand(sortedHand.stream()
                  .filter(card -> !exclude.contains(card))
                  .collect(Collectors.toList())));
         }
      }
      return candidates;
   }


   private static HandRank getHandRank(PokerHand hand)
   {
      HandEvaluator value;
      if (isStraightFlush(hand.getHoleCards()))
      {
         value = STRAIGHT_FLUSH;
      } else if (isQuads(hand.getHoleCards()))
      {
         value = QUADS;
      } else if (isFullHouse(hand.getHoleCards()))
      {
         value = FULL_HOUSE;
      } else if (isFlush(hand.getHoleCards()))
      {
         value = FLUSH;
      } else if (isStraight(hand.getHoleCards()))
      {
         value = STRAIGHT;
      } else if (isThreeKind(hand.getHoleCards()))
      {
         value = THREE_KIND;
      } else if (isNumPairs(hand.getHoleCards(), 2))
      {
         value = TWO_PAIR;
      } else if (isNumPairs(hand.getHoleCards(), 1))
      {
         value = PAIR;
      } else
      {
         value = HIGH_CARD;
      }
      return new HandRank(value, getTieBreakers(value, hand.getHoleCards()));
   }

   private static boolean isStraightFlush(List<Card> hand)
   {
      return isFlush(hand) && isStraight(hand);
   }

   private static boolean isQuads(List<Card> hand)
   {
      Map<Card.Rank, List<Card>> collect =
            hand.stream().collect(Collectors.groupingBy(Card::getRank));

      for (Card.Rank rank : collect.keySet())
      {
         if (collect.get(rank).size() == 4)
         {
            return true;
         }
      }
      return false;
   }

   private static boolean isFullHouse(List<Card> hand)
   {
      return isThreeKind(hand) && ( isNumPairs(hand, 1) || isNumPairs(hand, 2) );
   }

   private static boolean isFlush(List<Card> hand)
   {
      Map<Card.Suit, List<Card>> collect =
            hand.stream().collect(Collectors.groupingBy(Card::getSuit));

      for (Card.Suit suit : collect.keySet())
      {
         if (collect.get(suit).size() == 5)
         {
            return true;
         }
      }
      return false;
   }

   private static boolean isStraight(List<Card> hand)
   {
      Map<String, Integer> validPatterns = new HashMap<>();
      StringBuilder builder = new StringBuilder();
      validPatterns.put("0 1 2 3 4 ", 4);
      validPatterns.put("0 1 2 3 12 ", 3);

      hand.forEach(card ->
      {
         builder.append(card.getRank().getVal() - hand.get(0).getRank().getVal());
         builder.append(" ");
      });
      int highInd = validPatterns.getOrDefault(builder.toString(), 0);

      return highInd > 0;
   }

   private static boolean isThreeKind(List<Card> hand)
   {
      Map<Card.Rank, List<Card>> collect =
            hand.stream().collect(Collectors.groupingBy(Card::getRank));

      for (Card.Rank rank : collect.keySet())
      {
         if (collect.get(rank).size() == 3)
         {
            return true;
         }
      }
      return false;
   }


   private static boolean isNumPairs(List<Card> hand, int num)
   {
      int numPairs = 0;

      Map<Card.Rank, List<Card>> collect =
            hand.stream().collect(Collectors.groupingBy(Card::getRank));

      for (Card.Rank rank : collect.keySet())
      {
         if (collect.get(rank).size() == 2)
         {
            numPairs++;
         }
      }

      return numPairs == num;
   }

   private static List<Integer> getTieBreakers(HandEvaluator value, List<Card> hand)
   {
      List<Integer> tieBreakers = new ArrayList<>();

      if (value == STRAIGHT_FLUSH || value == STRAIGHT)
      {
         int highIndex = (hand.get(0).getRank() == Card.Rank.TWO &&
               hand.get(4).getRank() == Card.Rank.ACE) ? 3 : 4;

         tieBreakers.add(hand.get(highIndex).getRank().getVal());
      }
      else if (value == QUADS)
      {
         tieBreakers.add(hand.get(2).getRank().getVal());
         if (!tieBreakers.contains(hand.get(0).getRank().getVal()))
         {
            tieBreakers.add(hand.get(0).getRank().getVal());
         }
         else
         {
            tieBreakers.add(hand.get(4).getRank().getVal());
         }
      }
      else if (value == FULL_HOUSE)
      {
         tieBreakers.add(hand.get(2).getRank().getVal());
         if (!tieBreakers.contains(hand.get(1).getRank().getVal()))
         {
            tieBreakers.add(hand.get(1).getRank().getVal());
         }
         else
         {
            tieBreakers.add(hand.get(3).getRank().getVal());
         }
      }
      else if(value == FLUSH || value == HIGH_CARD)
      {
         for (int i = hand.size() - 1; i >= 0; i--)
         {
            tieBreakers.add(hand.get(i).getRank().getVal());
         }
      }
      else if (value == THREE_KIND)
      {
         Map<Card.Rank, List<Card>> collect =
               hand.stream().collect(Collectors.groupingBy(Card::getRank));
         List<Integer> trips = new ArrayList<>();
         List<Integer> singles = new ArrayList<>();

         for (Card.Rank rank : collect.keySet())
         {
            if (collect.get(rank).size() == 3)
            {
               trips.add(rank.getVal());
            } else if (collect.get(rank).size() == 1)
            {
               singles.add(rank.getVal());
            }
         }
         trips.sort(Integer::compareTo);
         Collections.reverse(trips);
         singles.sort(Integer::compareTo);
         Collections.reverse(singles);

         tieBreakers.addAll(trips);
         tieBreakers.addAll(singles);
      }
      else if (value == TWO_PAIR || value == PAIR)
      {
         Map<Card.Rank, List<Card>> collect =
               hand.stream().collect(Collectors.groupingBy(Card::getRank));
         List<Integer> pairs = new ArrayList<>();
         List<Integer> singles = new ArrayList<>();

         for (Card.Rank rank : collect.keySet())
         {
            if (collect.get(rank).size() == 2)
            {
               pairs.add(rank.getVal());
            } else if (collect.get(rank).size() == 1)
            {
               singles.add(rank.getVal());
            }
         }
         pairs.sort(Integer::compareTo);
         Collections.reverse(pairs);
         singles.sort(Integer::compareTo);
         Collections.reverse(singles);

         tieBreakers.addAll(pairs);
         tieBreakers.addAll(singles);
      }

      return tieBreakers;
   }
}


class HandRank
{
   public final HandEvaluator HAND_VALUE;
   public final List<Integer> TIE_BREAKERS;

   HandRank(final HandEvaluator HAND_VALUE, final List<Integer> TIE_BREAKERS)
   {
      this.HAND_VALUE = HAND_VALUE;
      this.TIE_BREAKERS = TIE_BREAKERS;
   }

   @Override
   public boolean equals(Object o)
   {
      if (!(o instanceof HandRank)
            || this.HAND_VALUE != ((HandRank) o).HAND_VALUE)
      {
         return false;
      }

      for (int i = 0; i < this.TIE_BREAKERS.size(); i++)
      {
         if (!this.TIE_BREAKERS.get(i).equals(((HandRank) o).TIE_BREAKERS.get(i)))
         {
            return false;
         }
      }

      return true;
   }

   @Override
   public String toString()
   {
      StringBuilder builder = new StringBuilder();
      if (HAND_VALUE == HandEvaluator.FULL_HOUSE)
      {
         builder.append(HAND_VALUE).append(" ").append(TIE_BREAKERS.get(0))
               .append(" full of ").append(TIE_BREAKERS.get(1));
      }

      return builder.toString();
   }
}

class CompareHands implements Comparator<PokerHand>
{
   private CommunityCards commCards;
   CompareHands(CommunityCards commCards)
   {
      this.commCards = commCards;
   }

   CompareHands()
   {
      commCards = new CommunityCards();
   }

   @Override
   public int compare(PokerHand hand1, PokerHand hand2)
   {
      HandRank val1 = HandEvaluator.getBestHand(hand1, commCards);
      HandRank val2 = HandEvaluator.getBestHand(hand2, commCards);

      //different handVals
      if (val1.HAND_VALUE.ordinal() > val2.HAND_VALUE.ordinal())
      {
         return 1;
      }
      else if (val1.HAND_VALUE.ordinal() < val2.HAND_VALUE.ordinal())
      {
         return -1;
      }
      //same handVals, analyze if one is made up of stronger cards
      else
      {
         for (int i = 0; i < val1.TIE_BREAKERS.size(); i++)
         {
            if (val1.TIE_BREAKERS.get(i) > val2.TIE_BREAKERS.get(i))
            {
               return 1;
            }
            else if (val1.TIE_BREAKERS.get(i) < val2.TIE_BREAKERS.get(i))
            {
               return -1;
            }
         }
      }
      return 0;
   }
}
