import Poker.Deck.Card;
import Poker.Deck.EmptyDeckException;
import Poker.Deck.StandardDeck;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;

public class DeckTest
{
   @Test
   public void StandardDeckTest()
   {
      StandardDeck deck = new StandardDeck();
      Set<Card> cards = new HashSet<>();
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

   @Test
   public void sizeTest()
   {

   }
}
