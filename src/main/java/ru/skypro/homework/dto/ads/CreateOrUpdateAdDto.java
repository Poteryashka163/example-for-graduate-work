package ru.skypro.homework.dto.ads;

import lombok.Data;
import ru.skypro.homework.model.Ad;

import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateAdDto {
    @Size(min = 4, max = 32)
    private String title;
    @Size(max = 10000000)
    private int price;
    @Size(min = 8, max = 64)
    private String description;

    public Ad toAd() {
        Ad ad = new Ad();
        ad.setTitle(this.getTitle());
        ad.setPrice(this.getPrice());
        ad.setDescription(this.getDescription());
        return ad;
    }
}
