package br.com.pizzaplanet.config;

import br.com.pizzaplanet.config.model.Badge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BadgeContext {

  private static final ThreadLocal<Badge> BADGE_THREAD_LOCAL = new ThreadLocal<>();

  public static void setBadge(Badge badge) {
    BADGE_THREAD_LOCAL.set(badge);
  }

  public static Badge getBadge() {
    if (BADGE_THREAD_LOCAL.get() == null) {
      return new Badge();
    } else {
      return BADGE_THREAD_LOCAL.get();
    }
  }

  public static void setUser(UUID userId) {
    if (BADGE_THREAD_LOCAL.get() != null) {
      BADGE_THREAD_LOCAL.get().setUserId(userId);
    } else {
      Badge badge = new Badge();
      badge.setUserId(userId);
      setBadge(badge);
    }
  }

  public static UUID getUser() {
    if (BADGE_THREAD_LOCAL.get() != null && BADGE_THREAD_LOCAL.get().getUserId() != null) {
      return BADGE_THREAD_LOCAL.get().getUserId();
    }
    return null;
  }
}
