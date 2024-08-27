package ru.skypro.homework.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.model.ImageUser;

/**
 * Класс-оболочка для получения изображения.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {

    private String id;

    private byte [] image;

    /**
     * Метод сопоставления изображения объекта с классом-оболочкой.
     */
    public static ImageDto fromImage(ImageUser imageUser) {
        return new ImageDto(imageUser.getId(), imageUser.getImage());
    }
}
