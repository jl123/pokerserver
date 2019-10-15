package Poker.Table;

import Poker.Deck.StandardDeck;
import Poker.Games.NoLimitHoldEm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.awt.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toCollection;

public class Table
{
   private static int num_tables_created = 999;
   public final int MAX_BUY_IN;
   public final int MIN_BUY_IN;
   final private NumSeats NUM_SEATS;
   private final List<Seat> SEATS;

   final int TABLE_ID;

   private NoLimitHoldEm game;
   CommunityCards communityCards;
   private StandardDeck deck;
   private int smallBlind;
   private int bigBlind;
   private int buttonPos;
   private int smallBlindPos;
   private int bigBlindPos;
   private int ante;


   public Table(final NumSeats NUM_SEATS, final int MIN_BUY_IN, final int MAX_BUY_IN,
                int smallBlind, int bigBlind)
   {
      TABLE_ID = getNewTableID();
      this.NUM_SEATS = NUM_SEATS;
      this.MIN_BUY_IN = MIN_BUY_IN;
      this.MAX_BUY_IN = MAX_BUY_IN;
      this.smallBlind = smallBlind;
      this.bigBlind = bigBlind;
      deck = new StandardDeck();
      communityCards = new CommunityCards();

      SEATS = new ArrayList<>();
//      for (int i = 0; i < NUM_SEATS.value; i++)
//      {
//         SEATS.add(new Seat(i, game));
//      }
   }

   public void newHand()
   {
      SEATS.forEach(seat -> seat.hand = new PokerHand());
   }

   public enum NumSeats
   {
      TWO(2), SIX(6), NINE(9);

      int value;

      NumSeats(int value)
      {
         this.value = value;
      }
   }

   private Image getTableImage()
   {
      //retrieve image for proper num players.
      //
      return null;
   }

   public String getAvailableSeats()
   {
      StringBuilder builder = new StringBuilder("Available seats:\n");
      SEATS.stream().filter(s -> s.getSeatState() == Seat.SeatState.ACTIVE).forEach(seat -> builder.append(seat.SEAT_NUM).append(" "));

      return builder.toString();
   }

   public void takeSeat(Player player)
   {
      int seatNum = -1;
      do
      {
         try
         {

            System.out.println(getAvailableSeats());
            Scanner scanner = new Scanner(System.in);
            seatNum = scanner.nextInt();
         }
         catch (InputMismatchException e)
         {
            System.out.println("Invalid input.");
         }
                                                         //NEED TRY CATCH
      }while(seatNum < 0 || seatNum >= NUM_SEATS.value || SEATS.get(seatNum).isActive());

      //BUY IN PROMPT
      SEATS.get(seatNum).setPlayer(player);
      SEATS.get(seatNum).buyChips(MIN_BUY_IN, MAX_BUY_IN);


   }

   public void setGame(NoLimitHoldEm game)
   {
      this.game = game;
   }

   public List<Seat> getPlayersInHand(List<Seat> seats)
   {
      return seats.stream()
            .filter(Seat::isActive)
            .collect(toCollection(ArrayList::new));

   }

   public String getPlayerList(List<Seat> seats)
   {
      StringBuilder builder = new StringBuilder("PokerTable.Player List:\n");
      seats.stream().filter(Seat::isActive).forEach(builder::append);
      return builder.toString();
   }

   public static void main(String[] args)
   {
      Table table = new Table(NumSeats.SIX, 50, 200, 1, 2);
      NoLimitHoldEm game = new NoLimitHoldEm(1, 2, 8);
      table.setGame(game);
      Player p1 = new Player("Joe");
      Player p2 = new Player("Ali");
      table.takeSeat(p1);
      table.takeSeat(p2);

      table.game.processPreFlop();

      table.SEATS.stream().filter(Seat::isActive)
            .forEach(seat -> System.out.println(seat + "HAND: " + seat.hand));

      game.processFlop();
      game.processTurn();
      game.processRiver();
      System.out.println("FLOP:\n" + table.communityCards.toString());
      List<PokerHand> hands = table.SEATS.stream().map(seat -> seat.hand)
            .collect(toCollection(ArrayList::new));
      List<Seat> playerList = table.getPlayersInHand(table.SEATS);
   }

   @Override
   public boolean equals(Object o)
   {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Table table = (Table) o;

      if (TABLE_ID != table.TABLE_ID) return false;
      if (bigBlindPos != table.bigBlindPos) return false;
      return ante == table.ante;
   }

   @Override
   public int hashCode()
   {
      return TABLE_ID;
   }

   public int gettableID()
   {
      return TABLE_ID;
   }

   private static int getNewTableID()
   {
      return ++num_tables_created;
   }
}

