package com.example.kotobaza.services;

import com.example.kotobaza.modeles.City;
import com.example.kotobaza.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public List<City> getAllCity() {
        return cityRepository.findAll();
    }

    public City getCity(Long id) {
        return cityRepository.findById(id).orElseThrow(() -> new RuntimeException("Город не найден"));
    }
}
