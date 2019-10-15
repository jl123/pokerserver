package Poker.Table;

public class Player
{
   private String name;
   private int bank;

   public Player(String name)
   {
      this.name = name;
      bank = 2000;
   }

   public int getBank()
   {
      return bank;
   }
   //consider moving to seat
   public int buyIn(int chips)
   {
      if (bank >= chips)
      {
         bank -= chips;
         return chips;
      }
      return 0;
   }

   public void cashOut(int chips)
   {
      bank += chips;
   }

   public String getName()
   {
      return name;
   }

}
