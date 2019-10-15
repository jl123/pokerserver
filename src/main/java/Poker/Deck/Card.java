package Poker.Deck;

public class Card  implements Comparable<Card>
{
   final private Rank rank;
   final private Suit suit;

   public Card(Rank rank, Suit suit)
   {
      this.rank = rank;
      this.suit = suit;
   }
   //copy constructor
   public Card(Card card)
   {
      this.rank = card.rank;
      this.suit = card.suit;
   }
   //test constructor
   public Card(String s)
   {
      this.rank = Rank.setRank(s.charAt(0));
      this.suit = Suit.setSuit(s.charAt(1));
   }
   //getters
   public Rank getRank()
   {
      return this.rank;
   }

   public Suit getSuit()
   {
      return this.suit;
   }

   public boolean same(Card card)
   {
      return this.suit == card.suit && this.rank == card.rank;
   }

   @Override
   public String toString()
   {
      return rank.toString() + suit;
   }

   public enum Rank
   {
      TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14);

      private int val;

      Rank(int val)
      {
         this.val = val;
      }

      public int getVal()
      {
         return val;
      }

      static Rank setRank(char ch)
      {
         Rank retRank = TWO;
         switch (ch)
         {
            case 'A':
               retRank = ACE;
               break;
            case 'K':
               retRank = KING;
               break;
            case 'Q':
               retRank = QUEEN;
               break;
            case 'J':
               retRank = JACK;
               break;
            case 'T':
               retRank = TEN;
               break;
            default:
               int faceVal = Character.getNumericValue(ch);
               for (Rank rank : Rank.values())
               {
                  if (faceVal == rank.getVal())
                  {
                     retRank = rank;
                     break;
                  }
               }
               break;
         }
         return retRank;
      }

      public String toString()
      {
         return val > 9 ? name().substring(0,1): String.valueOf(getVal());
      }
   }

   public enum Suit
   {
      S, D, C, H;

      public static Suit setSuit(char c)
      {
         Suit retSuit;
         switch (c)
         {
            case 'S':
               retSuit = S;
               break;
            case 'D':
               retSuit = D;
               break;
            case 'C':
               retSuit = C;
               break;
            default:         // should be H
               retSuit = H;
               break;
         }
         return retSuit;
      }
   }


   @Override
   public int compareTo(Card other)
   {
      if (this.rank.val > other.rank.val) { return 1; }
      else if (this.rank.val < other.rank.val) { return - 1; }
      else { return 0; }
   }

   @Override
   public boolean equals(Object o)
   {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Card other = (Card) o;

      return this.suit == other.suit && this.rank == other.rank;
   }

   @Override
   public int hashCode()
   {
      int result = rank.hashCode();
      result = 31 * result + suit.hashCode();
      return result;
   }

   public static void main(String[] args)
   {
      Card c1 = null;
      System.out.println(new Card(Rank.JACK, Suit.D).hashCode());
      System.out.println(new Card(Rank.JACK, Suit.D).hashCode());
   }
}

