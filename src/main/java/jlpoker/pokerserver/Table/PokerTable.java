package jlpoker.pokerserver.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.websocket.OnClose;
import javax.websocket.server.ServerEndpoint;

import java.util.Date;
import java.util.Objects;

@Entity(name = "poker_table")
public
class PokerTable
{
   @GeneratedValue
   @Column(name = "created")
   final Date CREATED = new Date();
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   @JsonProperty("game")
   @Column(name="game")
   @NotBlank
   private String game;
   @JsonProperty("blindSmall")
   @Column(name="blind_small")
   private double blindSmall;
   @JsonProperty("blindBig")
   @Column(name="blind_big")
   private double blindBig;

   public Integer getId() { return id; }

   public String getGame()
   {
      return game;
   }

   public double getBlindSmall()
   {
      return blindSmall;
   }

   public double getBlindBig()
   {
      return blindBig;
   }

   @Override
   public String toString()
   {
      return "PokerTable{" +
            "\nid = " + id +
            "\ngame = " + game +
            "\nblindSmall = " + blindBig +
            "\nblindBig = " + blindBig +
            "\ntime created = " + CREATED +
            '}' + "\n---------------------------";
   }


   public boolean equals(Object o)
   {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      PokerTable that = (PokerTable) o;

      if (Double.compare(that.blindSmall, blindSmall) != 0) return false;
      if (Double.compare(that.blindBig, blindBig) != 0) return false;
      if (!CREATED.equals(that.CREATED)) return false;
      if (!Objects.equals(id, that.id)) return false;
      return game.equals(that.game);
   }

   @Override
   public int hashCode()
   {
      int result;
      long temp;
      result = CREATED.hashCode();
      result = 31 * result + game.hashCode();
      temp = Double.doubleToLongBits(blindSmall);
      result = 31 * result + (int) (temp ^ (temp >>> 32));
      temp = Double.doubleToLongBits(blindBig);
      result = 31 * result + (int) (temp ^ (temp >>> 32));
      return result;
   }
}

