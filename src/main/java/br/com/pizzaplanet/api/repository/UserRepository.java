package br.com.pizzaplanet.api.repository;

import br.com.pizzaplanet.api.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByUsername(String username);
}
