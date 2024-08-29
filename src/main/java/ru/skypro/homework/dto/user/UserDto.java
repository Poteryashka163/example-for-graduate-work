package ru.skypro.homework.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.model.User;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
    private String image;


    /**
     * Метод сопоставления сущностей Users с классом-берткой для получения данных в профиле пользователя
     */
    public static UserDto toUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setRole(user.getRole());
        Optional.ofNullable(user.getImageUser()).ifPresent(image -> userDto.setImage(
                "/users/" + user.getImageUser().getId() + "/image"));
        return userDto;
    }
}

