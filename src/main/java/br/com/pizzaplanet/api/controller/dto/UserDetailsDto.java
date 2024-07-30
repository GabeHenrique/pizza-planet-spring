package br.com.pizzaplanet.api.controller.dto;

import br.com.pizzaplanet.api.repository.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDto {

  UUID id;
  String username;

  public static UserDetailsDto fromEntity(User user) {
    var dto = new UserDetailsDto();
    BeanUtils.copyProperties(user, dto);
    return dto;
  }
}
