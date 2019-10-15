package Poker.Table;

import Poker.Deck.Card;

import java.util.Scanner;

public class Seat
{
   public final int SEAT_NUM;
   private Player player;
   private int chips;
   private  SeatState seatState;
   PokerHand hand;
   //MOVE TO GAME?
   public enum SeatState
   {
      EMPTY, SITTING_OUT, ACTIVE
   }

   public Seat(final int SEAT_NUM)
   {
      this.SEAT_NUM = SEAT_NUM;
      seatState = SeatState.EMPTY;
      newHand();
   }

   public void processLeave()
   {
      player.cashOut(chips);
      chips = 0;
      seatState = SeatState.EMPTY;
      player = null;
   }

   public String getPlayerName()
   {
      return player == null ? "***EMPTY SEAT***" : player.getName();
   }

   public boolean setPlayer(Player player)
   {
      this.player = isEmpty() ? player : this.player;
      seatState = SeatState.ACTIVE;
      return this.player == player;
   }

   public void newHand()
   {
      hand = new PokerHand();
   }

   public SeatState getSeatState()
   {
      return seatState;
   }

   public boolean isActive() { return seatState == SeatState.ACTIVE; }

   public boolean isEmpty() { return seatState == SeatState.EMPTY; }
   //possibly move from table
   public void buyChips(final int MIN_BUY_IN, final int MAX_BUY_IN)
   {
      Scanner scanner = new Scanner(System.in);
      int buyInAmt = 0;
      do
      {
         System.out.println("How much would you like to add between " +
               MIN_BUY_IN + " and " + MAX_BUY_IN);
         try
         {
            buyInAmt = scanner.nextInt();
         }
         catch (Error e)
         {
            e.getMessage();
         }
      }while (buyInAmt < MIN_BUY_IN || buyInAmt > MAX_BUY_IN);

      chips = player.buyIn(buyInAmt);
   }

   public void takeCard(Card card)
   {
      hand.takeCard(card);
   }

   public int getChips()
   {
      return chips;
   }

   @Override
   public String toString()
   {

      return "SEAT STATUS----------------\n" +
            "\nSEAT NUM: " + SEAT_NUM +
            "\nActive: "+ isActive() +
            "\nNAME: " + getPlayerName() +
            "\nCHIPS: " + getChips()  +
            "\nHAND: " + hand.toString() +
            "Active: "+ isActive();
   }
}
