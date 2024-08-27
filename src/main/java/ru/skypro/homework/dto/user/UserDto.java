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
    private int IdDto;
    private String emailDto;
    private String firstNameDto;
    private String lastNameDto;
    private String phoneDto;
    private Role roleDto;
    private String imageDto;


    /**
     * Метод сопоставления сущностей Users с классом-берткой для получения данных в профиле пользователя
     */
    public static UserDto toUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setIdDto(user.getId());
        userDto.setEmailDto(user.getEmail());
        userDto.setPhoneDto(user.getPhone());
        userDto.setFirstNameDto(user.getFirstName());
        userDto.setLastNameDto(user.getLastName());
        userDto.setRoleDto(user.getRole());
        Optional.ofNullable(user.getImageUser()).ifPresent(image -> userDto.setImageDto(
                "/user/" + user.getImageUser().getId() + "/image"));
        return userDto;
    }
}

