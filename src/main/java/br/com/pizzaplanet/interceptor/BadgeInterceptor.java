package br.com.pizzaplanet.interceptor;

import br.com.pizzaplanet.api.service.UserService;
import br.com.pizzaplanet.config.BadgeContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class BadgeInterceptor implements HandlerInterceptor {

  private final UserService userService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler) {
    resolveUsuario(request);
    return true;
  }

  private void resolveUsuario(HttpServletRequest request) {
    var contextPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (contextPrincipal instanceof String) {
      try {
        UUID uid = UUID.fromString((String) contextPrincipal);
        userService.findUserById(uid)
            .ifPresent(user -> {
              BadgeContext.setUser(user.getId());
            });
      } catch (IllegalArgumentException exception) {
        // ignore
      }
    }
  }
}
