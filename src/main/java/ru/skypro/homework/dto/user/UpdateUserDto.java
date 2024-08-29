package ru.skypro.homework.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.model.User;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Класс-обёртка вводит данные для обновления информации (имя, фамилия и телефон) с проверкой.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto {
    @Size(message = "введите от 3 до 10 символов", min = 3, max = 10)
    private String firstName;
    @Size(message = "введите от 3 до 10 символов", min = 3, max = 10)
    private String lastName;
    @Pattern(message = "введите номер телефона согласно шаблона +7(777)777-77-77", regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    /**
     * Метод сопоставления пользователей сущности формы с классом-оболочкой для возврата изменяемых данных в базе данных.
     */
    public static UpdateUserDto toUpdateUser(User user) {
        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setPhone(user.getPhone());
        updateUserDto.setFirstName(user.getFirstName());
        updateUserDto.setLastName(user.getLastName());
        return updateUserDto;
    }
}
