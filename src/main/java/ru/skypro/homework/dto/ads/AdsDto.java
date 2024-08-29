package ru.skypro.homework.dto.ads;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AdsDto {
    private int count;
    private List<AdDto> results;

    public AdsDto(List<AdDto> results) {
        this.results = results;
        this.count = results.size();
    }

}
