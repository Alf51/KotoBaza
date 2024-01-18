package com.example.kotobaza.services;

import com.example.kotobaza.modeles.City;
import com.example.kotobaza.modeles.SuperCat;
import com.example.kotobaza.repository.CityRepository;
import com.example.kotobaza.utils.exeptions.CityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final CatService catService;

    public List<City> getAllCity() {
        return cityRepository.findAll();
    }

    public City getCityById(Long id) {
        return cityRepository.findById(id).orElseThrow(() -> new CityException("Город не найден"));
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

    @Transactional
    public void assignSuperCat(Long catId, Long CityId) {
        SuperCat superCat = catService.getSuperCatId(catId);
        City city = getCityById(CityId);
        city.addSuperCatInList(superCat);
    }

    @Transactional
    public void releaseSuperCatCat(Long catId, Long CityId) {
        SuperCat superCat = catService.getSuperCatId(catId);
        City city = getCityById(CityId);
        city.releaseSuperCatInList(superCat);
    }
}
