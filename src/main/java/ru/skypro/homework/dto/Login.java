package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Класс-обёртка с входными данными для входа пользователя в систему с проверкой.
 */

@Data
public class Login {
    @NotNull
    @Size(min = 4, max = 32, message = "введите от 4 до 32 символов")
    private String username;
    @NotNull
    @Size(min = 8, max = 16, message = "введите от 8 до 16 символов")
    private String password;

}
