package jlpoker.pokerserver.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

import javax.annotation.PostConstruct;
import javax.persistence.OneToMany;
import javax.websocket.OnOpen;
import java.util.*;
import java.util.stream.IntStream;

import static java.lang.Thread.sleep;

@Configuration
@EnableWebSocketMessageBroker
@RequestMapping(path = "/tables")
public class TableController
{
   @Autowired
   private TableRepository tableRepository;

   private Map<Integer, PokerTable> tableMap = new HashMap<>();

   @GetMapping(path = "/id/{id}")
   @ResponseBody
   public Optional<PokerTable> findById(@PathVariable String id)
   {
      return Optional.of(tableMap.get(id));
   }

   @GetMapping(path = "/get/{id}")
   @ResponseBody
   public Optional<PokerTable> get(@PathVariable String id)
   {
      System.out.println(tableMap.size());
      if (tableMap.isEmpty())
      {
         fillTableMap();
      }
      return Optional.of(tableMap.getOrDefault(Integer.valueOf(id), null));
   }

   @PostMapping(path = "/add")
   public @ResponseBody
   PokerTable add(@RequestBody PokerTable table)
   {
      try
      {
         System.out.println("SLEEP--------------------------");
         sleep(500);
      } catch (InterruptedException e)
      {
         e.printStackTrace();
      }
      tableRepository.save(table);
      return table;
   }

   @GetMapping(path = "/all")
   @ResponseBody
   public Iterable<PokerTable> getAllTables()
   {
      return tableMap.values();
   }

   @GetMapping(path = "tabletest")
   @ResponseBody
   public void tableTest()
   {
      IntStream.range(0, 5).forEach(i -> System.out.println(new PokerTable().getId()));
   }

   @PostConstruct
   private void fillTableMap()
   {
      tableRepository.findAll().forEach(table -> tableMap.put(table.getId(), table));
   }
}
