package ru.skypro.homework.dto.ads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.model.ImageAd;

/**
 * Класс-обертка для получения картинки объявления.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {
    private String id;
    private byte[] image;

    public static ImageDto fromImageAd(ImageAd image) {

        return new ImageDto(image.getId(), image.getImage());
    }
}
