package Poker.Table;//package PokerTable;
//
//import java.util.*;
//
//public class Main
//{
//   final static int NUM_TIMES = 500;
//   final static int NUM_TIMES_HEADS_UP = 50;
//   static int total = 0;
//
//   public static void main(String[] args)
//   {
//      StandardDeck.StandardDeck deck = new StandardDeck.StandardDeck();
//
//      deck.resetDeck();
//      PokerHand holdemHand = new PokerHand();
//      PokerHand secondHand = new PokerHand();
//
//      CommunityCards communityCards = new CommunityCards();
//      for (int i = 0; i < communityCards.MAX_CARDS; i++)
//      {
//         communityCards.takeCard(deck.dealCard());
//      }
//
//      holdemHand.takeCard(new StandardDeck.StandardDeck.StandardDeck.StandardDeck.StandardDeck.StandardDeck(StandardDeck.StandardDeck.StandardDeck.StandardDeck.StandardDeck.StandardDeck.Rank.ACE, StandardDeck.StandardDeck.StandardDeck.StandardDeck.StandardDeck.StandardDeck.Suit.S));
//      holdemHand.takeCard(new StandardDeck.StandardDeck.StandardDeck.StandardDeck.StandardDeck.StandardDeck(StandardDeck.StandardDeck.StandardDeck.StandardDeck.StandardDeck.StandardDeck.Rank.ACE, StandardDeck.StandardDeck.StandardDeck.StandardDeck.StandardDeck.StandardDeck.Suit.H));
//      secondHand.takeCard(new StandardDeck.StandardDeck.StandardDeck.StandardDeck.StandardDeck.StandardDeck(StandardDeck.StandardDeck.StandardDeck.StandardDeck.StandardDeck.StandardDeck.Rank.TEN, StandardDeck.StandardDeck.StandardDeck.StandardDeck.StandardDeck.StandardDeck.Suit.S));
//      secondHand.takeCard(new StandardDeck.StandardDeck.StandardDeck.StandardDeck.StandardDeck.StandardDeck(StandardDeck.StandardDeck.StandardDeck.StandardDeck.StandardDeck.StandardDeck.Rank.TEN, StandardDeck.StandardDeck.StandardDeck.StandardDeck.StandardDeck.StandardDeck.Suit.H));
//      holdemHand.getHandVal(communityCards);
//
//      System.out.println(holdemHand);
//      communityCards.getCards().stream().forEach(x -> System.out.print(x + " "));
//
//   }
//
//}
