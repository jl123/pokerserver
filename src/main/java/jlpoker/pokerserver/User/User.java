package jlpoker.pokerserver.User;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "user") // This tells Hibernate to make a table out of this class
public class User {
   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   private Integer id;
   @JsonProperty("name")
   private String name;
   @JsonProperty("email")
   private String email;

   public Integer getId() { return id; }

   public String getName() { return name; }

   public String getEmail() { return email; }

   @Override
   public String toString()
   {
      return "User{" +
            "\nid=" + id +
            "\n name='" + name +
            "\n email='" + email +
            '}';
   }

   @Override
   public boolean equals(Object o)
   {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      User user = (User) o;

      if (!id.equals(user.id)) return false;
      if (name != null ? !name.equals(user.name) : user.name != null)
         return false;
      return email != null ? email.equals(user.email) : user.email == null;
   }

   public boolean setId(Integer id)
   {
      if( this.id != null) { return false; }

      this.id = id;
      return true;
   }

   @Override
   public int hashCode() { return id.hashCode(); }
}