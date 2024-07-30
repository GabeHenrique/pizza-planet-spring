package br.com.pizzaplanet.api.service.auth;

import br.com.pizzaplanet.api.repository.model.User;
import br.com.pizzaplanet.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  public String createAuthenticationToken(User user) {
    var token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
    var authentication = authenticationManager.authenticate(token);
    var principal = (User) authentication.getPrincipal();
    return jwtUtil.generateToken(principal);
  }

  public ResponseEntity<?> registerUser(br.com.pizzaplanet.api.repository.model.User user) {
    var existingUser = userService.findByUsername(user.getUsername());
    if (existingUser.isPresent()) {
      return ResponseEntity.badRequest().body("User already exists");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    var createdUser = userService.create(user);
    return ResponseEntity.ok().body(createdUser);
  }
}
