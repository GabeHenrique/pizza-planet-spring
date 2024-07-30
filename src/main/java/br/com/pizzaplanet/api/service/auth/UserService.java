package br.com.pizzaplanet.api.service.auth;

import br.com.pizzaplanet.api.repository.UserRepository;
import br.com.pizzaplanet.api.repository.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public User create(User user) {
    return userRepository.save(user);
  }
}
