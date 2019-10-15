package jlpoker.pokerserver;

import jlpoker.pokerserver.Table.TableController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.websocket.OnOpen;

@SpringBootApplication
public class PokerServerApplication
{
   @Autowired
   public static void main(String[] args)
   {
      SpringApplication.run(PokerServerApplication.class, args);
   }
}
