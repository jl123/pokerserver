package Poker.Deck;

public class EmptyDeckException extends Exception
{
   EmptyDeckException()
   {
      super("The deck is out of cards!");
   }
}