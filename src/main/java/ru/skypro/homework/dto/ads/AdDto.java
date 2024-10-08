package ru.skypro.homework.dto.ads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.model.Ad;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdDto {
    private int author;
    private int pk;
    private String image;
    private int price;
    private String title;

    public static AdDto fromAd(Ad ad) {
        AdDto adDto = new AdDto();
        adDto.setPk(ad.getPk());
        adDto.setAuthor(ad.getUser().getId());
        adDto.setTitle(ad.getTitle());
        adDto.setPrice(ad.getPrice());
        Optional.ofNullable(ad.getImage()).ifPresent(image -> adDto.setImage(
                "/ads/" + ad.getImage().getId() + "/image"));
        return adDto;
    }
}
