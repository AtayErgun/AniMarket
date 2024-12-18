package com.example.demo.repository;

import com.example.demo.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement,Long> {
    // Tür ve yaşa göre filtreleme
    @Query("SELECT k FROM Advertisement k WHERE (:tur IS NULL OR k.tur = :tur) AND (:minYas IS NULL OR k.yas >= :minYas) AND (:maxYas IS NULL OR k.yas <= :maxYas)")
    List<Advertisement> findByTurAndYas(@Param("tur") String tur, @Param("minYas") Integer minYas, @Param("maxYas") Integer maxYas);

    // Fiyat aralığına göre filtreleme
    @Query("SELECT k FROM Advertisement k WHERE (:minFiyat IS NULL OR k.fiyat >= :minFiyat) AND (:maxFiyat IS NULL OR k.fiyat <= :maxFiyat)")
    List<Advertisement> findByFiyatAraligi(@Param("minFiyat") BigDecimal minFiyat, @Param("maxFiyat") BigDecimal maxFiyat);

}
