package ru.skypro.homework.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

/**
 * Класс-оболочка вводит данные для обновления пароля с проверкой.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewPasswordDto {
    @Size(message = "введите от 8 до 16 символов", min = 8, max = 16)
    private String newPassword;
    @Size(message = "введите от 8 до 16 символов", min = 8, max = 16)
    public String currentPassword;

}
