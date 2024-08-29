package ru.skypro.homework.dto.ads;

import lombok.Data;
import ru.skypro.homework.model.Ad;

import java.util.Optional;

@Data
public class ExtendedAdDto {
    private Integer pk;
    private String authorFirstName;
    private String authorLastName;
    private String email;
    private String phone;
    private String description;
    private String title;
    private Integer price;
    private String image;


    public static ExtendedAdDto fromAd(Ad ad) {
        ExtendedAdDto extendedAd = new ExtendedAdDto();
        extendedAd.setPk(ad.getPk());
        extendedAd.setAuthorFirstName(ad.getUser().getFirstName());
        extendedAd.setAuthorLastName(ad.getUser().getLastName());
        extendedAd.setDescription(ad.getDescription());
        extendedAd.setEmail(ad.getUser().getEmail());
        extendedAd.setPhone(ad.getUser().getPhone());
        extendedAd.setTitle(ad.getTitle());
        extendedAd.setPrice(ad.getPrice());
        Optional.ofNullable(ad.getImage()).ifPresent(image -> extendedAd.setImage(
                "/ads/" + ad.getImage().getId() + "/image"));
        return extendedAd;
    }

}
