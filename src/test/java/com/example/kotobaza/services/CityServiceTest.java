package com.example.kotobaza.services;

import com.example.kotobaza.modeles.City;
import com.example.kotobaza.modeles.SuperCat;
import com.example.kotobaza.repository.CityRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
class CityServiceTest {
    @Mock
    private CatService catService;

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    @Test
    @DisplayName("должен добавить кота в конкретный город")
    void assignSuperCat_shouldAddSuperCatInCity() {
        Long catId = 1L;
        Long cityId = 1L;
        SuperCat superCat = getSuperCat();
        City city = getCity();

        Mockito.when(catService.getSuperCatId(catId)).thenReturn(superCat);
        Mockito.when(cityRepository.findById(cityId)).thenReturn(Optional.of(city));

        cityService.assignSuperCat(catId, cityId);

        SuperCat assignedSuperCat = city.getCatList().get(0);

        assertEquals(superCat, assignedSuperCat);
    }

    private SuperCat getSuperCat() {
        SuperCat superCat = new SuperCat();
        superCat.setId(1L);
        superCat.setAge(3);
        superCat.setSuperName("Кошачий глаз");
        superCat.setName("Брасик");
        return superCat;
    }

    private City getCity() {
        City city = new City();
        city.setId(1L);
        city.setName("Котоярск");
        city.setPopulation(2000);
        return city;
    }
}