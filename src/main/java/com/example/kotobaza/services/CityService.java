package com.example.kotobaza.services;

import com.example.kotobaza.modeles.City;
import com.example.kotobaza.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public List<City> getAllCity() {
        return cityRepository.findAll();
    }

    public City getCityById(Long id) {
        return cityRepository.findById(id).orElseThrow(() -> new RuntimeException("Город не найден"));
    }

    public City save(City city) {
        return cityRepository.save(city);
    }

    public Optional<City> getCityByName(String name) {
        return cityRepository.findByName(name);
    }

    public void deleteById(Long id) {
        cityRepository.deleteById(id);
    }
}
