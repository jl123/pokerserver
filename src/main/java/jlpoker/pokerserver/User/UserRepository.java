package jlpoker.pokerserver.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

   Optional<User> findByEmail(@JsonProperty("email") String email);
}