package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Ad;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для получения методов для работы с базой данных обьявленй.
 */
@Repository
public interface AdsRepository extends JpaRepository<Ad, Integer> {

    @Query(value = "SELECT * FROM ads", nativeQuery = true)
    List<Ad> findAllAds();


    @Query(value = "SELECT * FROM ads " +
            "WHERE user_id = :meId", nativeQuery = true)
    List<Ad> getAdsMe(Integer meId);

    Optional<Ad> findAdByPk(int pk);
}
