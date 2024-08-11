package ru.skypro.homework.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Data
public class Ad {
    private String imageAds;
    private int pkAd;
    private int price;
    private String title;

    public Ad() {
    }

    public Ad(String imageAds, int pkAd, int price, String title) {
        this.imageAds = imageAds;
        this.pkAd = pkAd;
        this.price = price;
        this.title = title;
    }
}
