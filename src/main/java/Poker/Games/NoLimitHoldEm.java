package Poker.Games;

import Poker.Deck.*;
import Poker.Deck.EmptyDeckException;
import Poker.Table.CommunityCards;
import Poker.Table.Player;
import Poker.Table.Seat;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


//heads up button is small blind.....need to implement
public class NoLimitHoldEm
{
   final public static int MAX_PLAYERS = 10;
   final static List NUM_SEATS_OPTIONS =
         Collections.unmodifiableList(Arrays.stream(new int[]{2, 6, 9, 10})
               .boxed().collect(Collectors.toList()));
   final static int DEFAULT_SEATS = (int) NUM_SEATS_OPTIONS.get(0);

   public final int NUM_HOLE_CARDS = 2;
   public final int FLOP_CARDS = 3;
   public final int TURN_CARDS = FLOP_CARDS + 1;
   public final int RIVER_CARDS = TURN_CARDS + 1;

   public final int NUM_SEATS;
   public final int BIG_BLIND;
   public final int SMALL_BLIND;
   public final Map<Integer, Seat> playerSeats;

   private CommunityCards communityCards;
   private int buttonPos;
   private int smallBlindPos;
   private int bigBlindPos;
   private int minBetRaise;
   private Step stage;
   private Deck deck;
   //List<>

   public NoLimitHoldEm(final int SMALL_BLIND, final int BIG_BLIND, final int NUM_SEATS)
   {
      stage = Step.NEW;
      this.BIG_BLIND = BIG_BLIND;
      this.SMALL_BLIND = SMALL_BLIND;

      this.NUM_SEATS = setNumSeats(NUM_SEATS);
      this.playerSeats = initializeSeats();
   }

   //CHANGE TO USING ENUM LATER
   private static int setNumSeats(final int NUM_SEATS)
   {
      return NUM_SEATS_OPTIONS.contains(NUM_SEATS) ? NUM_SEATS : DEFAULT_SEATS;
   }

   private Map<Integer, Seat> initializeSeats()
   {
      return IntStream.range(0, NUM_SEATS).boxed()
            .collect(Collectors.toMap(Integer::valueOf, Seat::new));
   }

   public enum Step
   {
      NEW(0), PRE_FLOP(0), FLOP(3), TURN(4), RIVER(5), EVALUATE(0);

      private int val;
      private static Step[] stepArr = Step.values();

      Step(int val)
      {
         this.val = val;
      }

      public int getVal()
      {
         return val;
      }

      public Step getNext()
      {
         int index = this.ordinal() + 1;
         return index >= stepArr.length ? stepArr[1] : stepArr[index];
      }
   }

   public boolean takeSeat(Player p, int seatNum)
   {
      return seatNum >= 0 && seatNum < playerSeats.size() &&
            playerSeats.get(seatNum).setPlayer(p);
   }

   public void nextHand()
   {
      if (deck == null) { deck = new StandardDeck(); }

      communityCards = new CommunityCards();
      deck.resetDeck();
   }

   private int moveButton(List<Seat> seats)
   {
      return findNextOccupiedSeat(buttonPos, seats);
   }

   private void moveSmallBlind(List<Seat> seats)
   {

      if (getPlayerCount(seats) == 2)
      {
         smallBlindPos = buttonPos;
      }
      smallBlindPos = findNextOccupiedSeat(buttonPos + 1, seats);
   }

   private void moveBigBlind(List<Seat> seats)
   {
      bigBlindPos = findNextOccupiedSeat(smallBlindPos + 1, seats);
   }

   private int findNextOccupiedSeat(int seatNum, List<Seat> seats)
   {
      int candidate = seatNum;
      do
      {
         candidate = (candidate + 1) >= playerSeats.size() ? 0 : (candidate + 1);

      }while(!seats.get(candidate).isActive() && candidate != seatNum);
      return seatNum;
   }

   public int bettingOrder(List<Seat> seats)
   {
      List<Integer> order = new ArrayList<>();
      if (stage == Step.PRE_FLOP)
      {
         if (getPlayerCount(seats) == 2)
         {
            order.add(buttonPos);
         }

      }
      return 1;
   }

   private int getPlayerCount(List<Seat> seats)
   {
      return (int) seats.stream()
            .filter(Seat::isActive).count();
   }

   public void processFlop()
   {

      dealCardsToCommunity();
      bettingRound();
   }

   public void processPreFlop()
   {
      nextHand();
      communityCards = new CommunityCards();
      dealToPlayers();
      bettingRound();
   }

   public void processTurn()
   {
      dealCardsToCommunity();
      bettingRound();
   }

   public void processRiver()
   {
      dealCardsToCommunity();
      bettingRound();
   }


   private void bettingRound()
   {
      List<Seat> order = new ArrayList<>();
   }

   private void dealToPlayers()
   {
      deck.resetDeck();
      try
      {
         deck.burnCard();
      }
      catch (EmptyDeckException e)
      {
         System.out.println(e.getMessage());
      }

      for (int i = 0; i < NUM_HOLE_CARDS; i++)
      {
         IntStream.range(0, playerSeats.size()).mapToObj(playerSeats::get)
               .filter(Seat::isActive).forEach(s -> {
                  try
                  {
                     s.takeCard(deck.dealCard());
                  } catch (EmptyDeckException e)
                  {
                     System.out.println(e.getMessage());
                  }
               });
      }
   }

   void dealCardsToCommunity()
   {
      while (communityCards.getNumCards() < stage.val)
      {
         try
         {
            deck.burnCard();
            communityCards.takeCards(deck.dealCard());
         } catch (EmptyDeckException e)
         {
            System.out.println(e.getMessage());
            System.out.println("Call a manager.");
         }
      }
   }

   public static void main(String[] args) throws EmptyDeckException
   {
      NoLimitHoldEm game = new NoLimitHoldEm(10, 25, 6);
      game.playerSeats.values().forEach(System.out::println);
      Player p1 = new Player("Ali");
      Player p2 = new Player("Joe");
      System.out.println(game.takeSeat(p1, 1));
      System.out.println(game.takeSeat(p2, 4));
      game.processPreFlop();
      game.playerSeats.values().forEach(System.out::println);
      Deck myDeck = new ShortDeck();
      while (!myDeck.isEmpty())
      {
         System.out.println(myDeck.dealCard());
      }
   }
}

