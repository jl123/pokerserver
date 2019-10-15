package Poker.Table;

class SeatTakenException extends Exception
{
   SeatTakenException()
   {
      super("This seat is taken. please choose another.");
   }
}
