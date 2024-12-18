package com.example.demo.service.impl;

import com.example.demo.dto.AdvertisementDto;
import com.example.demo.entity.Advertisement;
import com.example.demo.entity.AdvertisementStatus;
import com.example.demo.repository.AdvertisementRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Builder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Builder
@Service
public class AdvertisementServiceImpl implements com.example.demo.service.AdvertisementService {
    private final AdvertisementRepository repository;

    public AdvertisementServiceImpl(AdvertisementRepository repository) {
        this.repository = repository;
    }

    @Override
    public AdvertisementDto saveOrUpdate(AdvertisementDto kurbanIlani) {
        return toDto(repository.save(toEntity(kurbanIlani)));
    }

    private AdvertisementDto toDto(Advertisement save) {
        AdvertisementDto dto = new AdvertisementDto();
        dto.setAciklama(save.getAciklama());
        dto.setAd(save.getAd());
        dto.setCinsiyet(save.getCinsiyet());
        dto.setFiyat(save.getFiyat());
        dto.setYas(save.getYas());
        dto.setGuncellemeTarihi(save.getGuncellemeTarihi());
        dto.setOlusturmaTarihi(save.getOlusturmaTarihi());
        dto.setTur(save.getTur());
        dto.setStatus(save.getStatus());
        dto.setSaticiId(save.getSeller());
        return dto;
    }

    private Advertisement toEntity(AdvertisementDto kurbanIlani) {
        Advertisement dto = new Advertisement();
        dto.setAciklama(kurbanIlani.getAciklama());
        dto.setAd(kurbanIlani.getAd());
        dto.setCinsiyet(kurbanIlani.getCinsiyet());
        dto.setFiyat(kurbanIlani.getFiyat());
        dto.setYas(kurbanIlani.getYas());
        dto.setGuncellemeTarihi(kurbanIlani.getGuncellemeTarihi());
        dto.setOlusturmaTarihi(kurbanIlani.getOlusturmaTarihi());
        dto.setTur(kurbanIlani.getTur());
        dto.setSeller(kurbanIlani.getSaticiId());
        return dto;
    }

    @Override
    public List<AdvertisementDto> getAll() {
        List<Advertisement> advertisements = repository.findAll(); // Tüm varlıkları çekiyoruz
        return advertisements.stream() // Stream başlatıyoruz
                .map(this::toDto) // Her varlığı DTO'ya dönüştürüyoruz
                .toList(); // Listeye dönüştürüyoruz
    }

    @Override
    public Optional<AdvertisementDto> getById(Long id) {
        return repository.findById(id)
                .map(this::toDto);
    }

    @Override
    public List<AdvertisementDto> filterByTurAndYas(String tur, Integer minYas, Integer maxYas) {
        List<Advertisement> advertisements = repository.findByTurAndYas(tur, minYas, maxYas);
        return advertisements.stream()
                .map(ad -> new AdvertisementDto(ad.getTur(), ad.getYas(), ad.getFiyat()))
                .collect(Collectors.toList());
    }

    @Override
    public List<AdvertisementDto> filterByFiyatAraligi(BigDecimal minFiyat, BigDecimal maxFiyat) {
        List<Advertisement> advertisements = repository.findByFiyatAraligi(minFiyat, maxFiyat);
        return advertisements.stream()
                .map(ad -> new AdvertisementDto(ad.getTur(), ad.getYas(), ad.getFiyat()))
                .collect(Collectors.toList());
    }

    @Override
    public Page<AdvertisementDto> getAllPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size); // Sayfa ve boyut tanımlama
        return repository.findAll(pageable)
                .map(this::toDto);
    }

    @Override
    public AdvertisementDto updateAdvertisement(Long id, AdvertisementDto updateDto) {
        Advertisement advertisement = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Advertisement not found with id: " + id));

        advertisement.setAd(updateDto.getAd());
        advertisement.setAciklama(updateDto.getAciklama());
        advertisement.setFiyat(updateDto.getFiyat());
        advertisement.setGuncellemeTarihi(LocalDateTime.now());
        advertisement.setCinsiyet(updateDto.getCinsiyet());
        advertisement.setYas(updateDto.getYas());
        advertisement.setTur(updateDto.getTur());
        advertisement.setStatus(updateDto.getStatus());
        Advertisement updatedAdvertisement = repository.save(advertisement);

        return toDto(updatedAdvertisement);
    }

    @Override
    public AdvertisementDto updateStatus(Long id, AdvertisementStatus status) {
            Advertisement advertisement = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Advertisement not found with id: " + id));
            advertisement.setStatus(status);
            repository.save(advertisement);
            return toDto(advertisement);
        }


    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
