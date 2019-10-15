package jlpoker.pokerserver.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.runtime.FindProperty;
import jlpoker.pokerserver.Table.PokerTable;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.integration.annotation.Default;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.sun.activation.registries.LogSupport.log;

@Repository
public interface TableRepository extends CrudRepository< PokerTable, Integer>
{
//   @Query("SELECT DISTINCT id FROM poker_table WHERE game = :game AND blind_big = :big_blind AND blind_small = :small_blind AND created = :created")
//   Integer findID(@Param("game") String game, @Param("big_blind") String bigBlind, @Param("small_blind") String smallBlind, @Param("created") String created);
//   Optional<PokerTable> findById(Integer i);
}
