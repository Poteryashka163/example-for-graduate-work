package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Класс-сущность для сохранения изображений с аватара пользователя.
 */
@Entity
@Table(name = "image_ad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageAd {
    @Id
    private String id;

    private byte[] image;

}
