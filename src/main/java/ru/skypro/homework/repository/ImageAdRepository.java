package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.ImageAd;

/**
 * Хранилище для получения методов работы с базой данных изображений пользователя.
 */

public interface ImageAdRepository extends JpaRepository<ImageAd, String> {
}
