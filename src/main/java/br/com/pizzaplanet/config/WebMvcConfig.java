package br.com.pizzaplanet.config;

import br.com.pizzaplanet.interceptor.BadgeInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

  private final BadgeInterceptor badgeInterceptor;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins("*")
        .allowedHeaders("empresa", "X-TenantID", "Authorization", "Accept-Language", "Content-Type")
        .exposedHeaders("*")
        .allowedMethods("POST", "GET", "DELETE", "PUT", "PATCH", "HEAD", "OPTIONS");
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(badgeInterceptor);
  }

}
