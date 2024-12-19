package com.example.demo.dto;

import com.example.demo.entity.AdvertisementStatus;
import com.example.demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@AllArgsConstructor
@Builder
public class AdvertisementDto {
    private Long id;
    private String ad;
    private String tur;
    private BigDecimal fiyat;
    private String aciklama;
    private Integer yas;
    private String cinsiyet;
    private User saticiId;
    private LocalDateTime olusturmaTarihi;
    private LocalDateTime guncellemeTarihi;
    private AdvertisementStatus status;


    public AdvertisementDto(String tur, Integer yas, BigDecimal fiyat) {
        this.tur = tur;
        this.yas = yas;
        this.fiyat = fiyat;
    }

    public AdvertisementDto() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public BigDecimal getFiyat() {
        return fiyat;
    }

    public void setFiyat(BigDecimal fiyat) {
        this.fiyat = fiyat;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public Integer getYas() {
        return yas;
    }

    public void setYas(Integer yas) {
        this.yas = yas;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public User getSaticiId() {
        return saticiId;
    }

    public void setSaticiId(User saticiId) {
        this.saticiId = saticiId;
    }

    public LocalDateTime getOlusturmaTarihi() {
        return olusturmaTarihi;
    }

    public void setOlusturmaTarihi(LocalDateTime olusturmaTarihi) {
        this.olusturmaTarihi = olusturmaTarihi;
    }

    public LocalDateTime getGuncellemeTarihi() {
        return guncellemeTarihi;
    }

    public void setGuncellemeTarihi(LocalDateTime guncellemeTarihi) {
        this.guncellemeTarihi = guncellemeTarihi;
    }

    public AdvertisementStatus getStatus() {
        return status;
    }

    public void setStatus(AdvertisementStatus status) {
        this.status = status;
    }
}
