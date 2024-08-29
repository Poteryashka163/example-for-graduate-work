package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.ImageUser;

/**
 * Хранилище для получения методов работы с базой данных изображений пользователя.
 */

public interface ImageUserRepository extends JpaRepository<ImageUser, String> {
}
