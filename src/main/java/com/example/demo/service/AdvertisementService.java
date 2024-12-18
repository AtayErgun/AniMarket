package com.example.demo.service;

import com.example.demo.dto.AdvertisementDto;
import com.example.demo.entity.AdvertisementStatus;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AdvertisementService {

    public AdvertisementDto saveOrUpdate(AdvertisementDto kurbanIlani);

    public List<AdvertisementDto> getAll();

    public Optional<AdvertisementDto> getById(Long id);

    public List<AdvertisementDto> filterByTurAndYas(String tur, Integer minYas, Integer maxYas);

    public List<AdvertisementDto> filterByFiyatAraligi(BigDecimal minFiyat, BigDecimal maxFiyat);

    public Page<AdvertisementDto> getAllPaged(int page, int size);

    public AdvertisementDto updateAdvertisement(Long id, AdvertisementDto updateDto);

    public AdvertisementDto updateStatus(Long id, AdvertisementStatus status);

    public void deleteById(Long id);

}
