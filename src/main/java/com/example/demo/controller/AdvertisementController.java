package com.example.demo.controller;

import com.example.demo.dto.AdvertisementDto;
import com.example.demo.entity.AdvertisementStatus;
import com.example.demo.request.AdvertisementRequest;
import com.example.demo.service.impl.AdvertisementServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kurban-ilanlari")
public class AdvertisementController {

    private final AdvertisementServiceImpl service;

    public AdvertisementController(AdvertisementServiceImpl service) {
        this.service = service;
    }

    // İlan ekleme veya güncelleme
    @PostMapping
    public ResponseEntity<AdvertisementDto> saveOrUpdate(@RequestBody AdvertisementRequest kurbanIlani) {
        AdvertisementDto savedIlani = service.saveOrUpdate(toDto(kurbanIlani));
        return ResponseEntity.ok(savedIlani);
    }

    private AdvertisementDto toDto(AdvertisementRequest kurbanIlani) {
        AdvertisementDto dto = new AdvertisementDto();
        dto.setAciklama(kurbanIlani.getAciklama());
        dto.setAd(kurbanIlani.getAd());
        dto.setCinsiyet(kurbanIlani.getCinsiyet());
        dto.setFiyat(kurbanIlani.getFiyat());
        dto.setYas(kurbanIlani.getYas());
        dto.setGuncellemeTarihi(kurbanIlani.getGuncellemeTarihi());
        dto.setOlusturmaTarihi(kurbanIlani.getOlusturmaTarihi());
        dto.setTur(kurbanIlani.getTur());
        dto.setSaticiId(kurbanIlani.getSaticiId());
        return dto;
    }

    // Tüm ilanları listeleme
    @GetMapping
    public ResponseEntity<List<AdvertisementDto>> getAll() {
        List<AdvertisementDto> ilanlar = service.getAll();
        return ResponseEntity.ok(ilanlar);
    }

    // ID'ye göre ilan bulma
    @GetMapping("/filtrele")
    public ResponseEntity<List<AdvertisementDto>> filterByCriteria(
            @RequestParam(required = false) String tur,
            @RequestParam(required = false) Integer minYas,
            @RequestParam(required = false) Integer maxYas,
            @RequestParam(required = false) BigDecimal minFiyat,
            @RequestParam(required = false) BigDecimal maxFiyat) {

        List<AdvertisementDto> ilanlar;

        if (minFiyat != null || maxFiyat != null) {
            ilanlar = service.filterByFiyatAraligi(minFiyat, maxFiyat);
        } else {
            ilanlar = service.filterByTurAndYas(tur, minYas, maxYas);
        }

        return ResponseEntity.ok(ilanlar);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdvertisementDto> getById(@PathVariable Long id) {
        Optional<AdvertisementDto> kurbanIlani = service.getById(id);
        return kurbanIlani.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // İlan silme
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/advertisements")
    public ResponseEntity<Page<AdvertisementDto>> getPagedAdvertisements(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.getAllPaged(page, size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdvertisementDto> updateAdvertisement(
            @PathVariable Long id,
            @RequestBody AdvertisementDto updateDto) {
        AdvertisementDto updatedAdvertisement = service.updateAdvertisement(id, updateDto);
        return ResponseEntity.ok(updatedAdvertisement);
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<AdvertisementDto> updateStatus(
            @PathVariable Long id,
            @RequestParam AdvertisementStatus status) {
        AdvertisementDto updatedAdvertisement = service.updateStatus(id, status);
        return ResponseEntity.ok(updatedAdvertisement);
    }
}