package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.*;
import ru.skypro.homework.exception.AccessErrorException;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.ImageAd;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.ImageAdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;

import java.io.IOException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AdsServiceImpl implements AdsService {
    private final AdsRepository adsRepository;
    private final UserRepository usersRepository;
    private final ImageAdRepository imageAdRepository;

    public AdsServiceImpl(AdsRepository adsRepository,
                          UserRepository usersRepository,
                          ImageAdRepository imageAdRepository) {
        this.adsRepository = adsRepository;
        this.usersRepository = usersRepository;
        this.imageAdRepository = imageAdRepository;
    }


    private boolean isAdminOrOwnerAd(Authentication authentication, String ownerAd) {
        boolean isAdmin = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList())
                .contains("ROLE_ADMIN");

        boolean isOwnerAd = authentication.getName().equals(ownerAd);

        return isAdmin || isOwnerAd;

    }

    private User userMe(Authentication authentication) {
        return usersRepository.findByEmail(authentication.getName()).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public AdsDto getAllAds() {

        List<Ad> adList = adsRepository.findAllAds();

        List<AdDto> adDtoList = adList.stream()
                .map(AdDto::fromAd)
                .collect(Collectors.toList());

        return new AdsDto(adDtoList);
    }

    @Override
    public AdDto addAd(CreateOrUpdateAdDto properties, MultipartFile file, Authentication authentication) {

        String username = authentication.getName();
        User user = usersRepository.findByEmail(username).orElseThrow(UserNotFoundException::new);

        if (authentication.isAuthenticated()) {

            Ad ad = properties.toAd();
            ad.setUser(user);
            ad = adsRepository.save(ad);

            ImageAd image = new ImageAd();
            image.setId(ad.getPk().toString());

            try {
                byte[] imageBytes = file.getBytes();
                image.setImage(imageBytes);
            } catch (IOException e) {
                throw new RuntimeException();
            }
            ImageAd returnImage = imageAdRepository.save(image);
            ad.setImage(returnImage);

            return AdDto.fromAd(adsRepository.save(ad));

        } else {
            throw new AccessErrorException("You can't add an ad.");
        }
    }

    @Override
    public ExtendedAdDto getAds(int id, Authentication authentication) {
        if (authentication.isAuthenticated()) {
            Ad extendedAd = adsRepository.findAdByPk(id).orElseThrow(AdNotFoundException::new);
            return ExtendedAdDto.fromAd(extendedAd);
        } else {
            throw new AccessErrorException("");
        }
    }

    @Override
    public void removeAd(int id, Authentication authentication) {
        Ad deletedAd = adsRepository.findAdByPk(id).orElseThrow(AdNotFoundException::new);
        if (isAdminOrOwnerAd(authentication, deletedAd.getUser().getEmail())) {
            adsRepository.delete(deletedAd);
        } else {
            throw new AccessErrorException("You can't delete ads.");
        }
    }

    @Override
    public AdDto updateAds(int id, CreateOrUpdateAdDto updateAd, Authentication authentication) {
        Ad updatedAd = adsRepository.findAdByPk(id).orElseThrow(AdNotFoundException::new);
        if (isAdminOrOwnerAd(authentication, updatedAd.getUser().getEmail())) {
            updatedAd.setTitle(updateAd.getTitle());
            updatedAd.setPrice(updateAd.getPrice());
            updatedAd.setDescription(updateAd.getDescription());
            adsRepository.save(updatedAd);
        } else {
            throw new AccessErrorException("You can't update ads.");
        }

        return AdDto.fromAd(updatedAd);
    }


    @Override
    public AdsDto getAdsMe(Authentication authentication) {
        Integer meId = userMe(authentication).getId();
        return new AdsDto(adsRepository.getAdsMe(meId)
                .stream()
                .map(AdDto::fromAd)
                .collect(Collectors.toList()));
    }

    @Override
    public ImageDto updateImage(int id, MultipartFile file, Authentication authentication) {

        Ad ad = adsRepository.findAdByPk(id).orElseThrow(AdNotFoundException::new);

        if (isAdminOrOwnerAd(authentication, ad.getUser().getEmail())) {
            ImageAd image;
            if (!Objects.isNull(ad.getImage())) {
                image = imageAdRepository.findById(ad.getImage().getId()).orElse(new ImageAd());
            } else {
                image = new ImageAd();
                image.setId(ad.getPk().toString());
            }
            try {
                byte[] imageBytes = file.getBytes();
                image.setImage(imageBytes);
            } catch (IOException e) {
                throw new RuntimeException();
            }
            ImageAd returnImage = imageAdRepository.save(image);
            ad.setImage(image);
            adsRepository.save((ad));

            return ImageDto.fromImageAd(returnImage);

        } else {
            throw new AccessErrorException("You can't update the image.");
        }
    }

    @Override
    public byte[] getImage(String id) {
        ImageAd image = imageAdRepository.findById(id).orElseThrow();
        return image.getImage();
    }
}
