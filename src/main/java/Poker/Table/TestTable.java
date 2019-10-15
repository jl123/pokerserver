package Poker.Table;

public class TestTable
{
   public static void main(String[] args)
   {
//      (final NumSeats NUM_SEATS, final int MIN_BUY_IN, final int MAX_BUY_IN,
//      int smallBlind, int bigBlind)
      Table table = new Table(Table.NumSeats.SIX, 200, 200, 1, 2);
      Table table2 = new Table(Table.NumSeats.SIX, 200, 200, 1, 2);
      System.out.println(table.gettableID());
      System.out.println(table2.gettableID());
   }

   public static Table getTable()
   {
      return new Table(Table.NumSeats.SIX, 200, 200, 1, 2);
   }
}
