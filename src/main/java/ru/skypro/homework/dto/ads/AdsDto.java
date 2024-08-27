package ru.skypro.homework.dto.ads;

import lombok.Data;

import java.util.List;

@Data
public class AdsDto {
    private int countAds;
    private List<AdDto> results;

    public Ads(List<AdDto> results) {
        this.results = results;
        this.countAds = results.size();
    }

}
