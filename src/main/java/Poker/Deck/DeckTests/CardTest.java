import Poker.Deck.Card;
import Poker.Deck.EmptyDeckException;
import Poker.Deck.StandardDeck;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class CardTest
{
//   @Test
//   public void jokersTest()
//   {
//      //StandardDeck.StandardDeck deck = new StandardDeck.StandardDeck(true);
//      Map<Card.Rank, List<Card>> collect = Stream.generate(StandardDeck::new).map(deck ->
//            {
//               Card c = null;
//               try
//               {
//                  c = deck.dealCard();
//               } catch (EmptyDeckException e)
//               {
//                  System.out.println(e.getMessage());
//               }
//               return c;
//            }
//      ).filter( card -> card != null).limit(52).collect(Collectors.groupingBy(Card::getRank));
//      System.out.println(collect.size());
//      //assertTrue(collect.size() == 58);
//      List<Card> cards = new ArrayList<>();
//      for(Card.Rank rank : collect.keySet())
//      {
//         cards.addAll(collect.get(rank));
//      }
////      cards.sort();
//
//
//      cards.forEach(card -> {System.out.print(card); System.out.print(" ");});
////   }

   @Test
   public void DeckTest()
   {
      StandardDeck deck = new StandardDeck();
      List<Card> cards = new ArrayList<>();
      assertEquals(52, deck.getSize());




      while(!deck.isEmpty())
      {
         try
         {
            cards.add(deck.dealCard());
         } catch (EmptyDeckException e)
         {
            e.getMessage();
         }
      }
      assertEquals(52, cards.size());
   }

//   @Test
//   public void throwsExceptionWithSpecificType() {
//      thrown.expect(NullPointerException.class);
//      throw new NullPointerException();
//   }
//   private static int i = 0;
//
//   public static int getI()
//   {
//      return ++i;
//   }
}
