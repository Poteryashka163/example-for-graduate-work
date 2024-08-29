package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;

/**
 * Интерфейс со способами входа в систему и регистрации пользователей.
 */
public interface AuthService {
    boolean login(String userName, String password);

    boolean register(Register register);
}
