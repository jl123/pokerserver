package Poker.Table;

public class Controller
{
   public void getBuyin(Player player, int chips)
   {
      player.cashOut(chips);
   }
}
