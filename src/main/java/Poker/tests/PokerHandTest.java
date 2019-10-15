package Poker.tests;//import StandardDeck.Card;
//import HandEvaluator;
//import PokerTable.CommunityCards;
//import PokerTable.PokerHand;
//import org.junit.Test;
//import static org.junit.Assert.assertTrue;
//
//public class PokerHandTest
//{
//   @Test
//   public void handTest1()
//   {
//      Card aaa = new Card(Card.Rank.JACK, Card.Suit.H);
//      Card bbb = new Card(Card.Rank.JACK, Card.Suit.S);
//      Card ccc = new Card(Card.Rank.JACK, Card.Suit.C);
//      Card ddd = new Card(Card.Rank.TEN, Card.Suit.C);
//      Card eee = new Card(Card.Rank.THREE, Card.Suit.H);
//      PokerHand testHand = new PokerHand();
//      CommunityCards commCards = new CommunityCards();
//      assertTrue(testHand.getHoleCards().size() == 0);
//      testHand.takeCard(aaa);
//      testHand.takeCard(bbb);
//      testHand.takeCard(ccc);
//      testHand.takeCard(ddd);
//      testHand.takeCard(eee);
//
//      assertTrue(testHand.getHoleCards().size() == 5);
//      System.out.println("-----");
//      System.out.println(HandEvaluator.getBestHand(testHand, new CommunityCards()));
//   }
//}
