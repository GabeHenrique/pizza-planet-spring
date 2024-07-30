package br.com.pizzaplanet.api.service;

import br.com.pizzaplanet.api.controller.dto.RegisterUserDto;
import br.com.pizzaplanet.api.controller.dto.UserDetailsDto;
import br.com.pizzaplanet.api.repository.UserRepository;
import br.com.pizzaplanet.api.repository.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static br.com.pizzaplanet.config.BadgeContext.getUser;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public void create(User user) {
    userRepository.save(user);
  }

  public Optional<User> findUserById(UUID id) {
    return userRepository.findById(id);
  }

  public ResponseEntity<?> registerUser(RegisterUserDto userDto) {
    var existingUser = findByUsername(userDto.username());
    if (existingUser.isPresent()) {
      return ResponseEntity.badRequest().body("User already exists");
    }
    var newUser = User.builder()
        .username(userDto.username())
        .password(passwordEncoder.encode(userDto.password())).build();
    create(newUser);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  public ResponseEntity<UserDetailsDto> getDetailsFromLoggedUser() {
    var user = findUserById(getUser()).orElseThrow();
    return ResponseEntity.ok().body(UserDetailsDto.fromEntity(user));
  }
}
