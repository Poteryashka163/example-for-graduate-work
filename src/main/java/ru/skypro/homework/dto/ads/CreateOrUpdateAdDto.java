package ru.skypro.homework.dto.ads;

import lombok.Data;
import ru.skypro.homework.model.Ad;

import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateAdDto {
    @Size(min = 4, max = 32)
    private String titleCreateOrUpdateAdDto;
    @Size(max = 10000000)
    private int priceCreateOrUpdateAdDto;
    @Size(min = 8, max = 64)
    private String descriptionCreateOrUpdateAdDto;

    public Ad toAd() {
        Ad ad = new Ad();
        ad.setTitle(this.getTitleCreateOrUpdateAdDto());
        ad.setPrice(this.getPriceCreateOrUpdateAdDto());
        ad.setDescription(this.getDescriptionCreateOrUpdateAdDto());
        return ad;
    }
}
