package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.*;


/**
 * Интерфейс с методами получения, добавления, изменения, удаления объявлений.
 */
public interface AdsService {

    AdsDto getAllAds();

    AdDto addAd(CreateOrUpdateAdDto createAd, MultipartFile image, Authentication authentication);

    ExtendedAdDto getAds(int id, Authentication authentication);

    //    @PreAuthorize("principal.admin or #username == authentication.principal.username")
    void removeAd(int id, Authentication authentication);

    //    @PreAuthorize("principal.admin or #username == authentication.principal.username")
    AdDto updateAds(int id, CreateOrUpdateAdDto updateAd, Authentication authentication);

    AdsDto getAdsMe(Authentication authentication);

    //    @PreAuthorize("principal.admin or #username == authentication.principal.username")
    ImageDto updateImage(int id, MultipartFile image, Authentication authentication);

    byte[] getImage(String id);
}
