package com.example.kotobaza.services;

import com.example.kotobaza.modeles.City;
import com.example.kotobaza.modeles.SuperCat;
import com.example.kotobaza.repository.CityRepository;
import com.example.kotobaza.utils.exeptions.CatException;
import com.example.kotobaza.utils.exeptions.CityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
class CityServiceTest {
    private SuperCat superCat;
    private City city;

    @Mock
    private CatService catService;

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    @BeforeEach
    void prepareSuperCat() {
        superCat = new SuperCat();
        superCat.setId(1L);
        superCat.setAge(3);
        superCat.setSuperName("Кошачий глаз");
        superCat.setName("Барсик");
    }

    @BeforeEach
    void prepareCity() {
        city = new City();
        city.setId(1L);
        city.setName("Котоярск");
        city.setPopulation(2000);
    }


    @Test
    void assignSuperCat_shouldAddSuperCatInCity() {
        Long catId = 1L;
        Long cityId = 1L;

        Mockito.when(catService.getSuperCatId(catId)).thenReturn(superCat);
        Mockito.when(cityRepository.findById(cityId)).thenReturn(Optional.of(city));

        cityService.assignSuperCat(catId, cityId);

        SuperCat assignedSuperCat = city.getCatList().get(0);

        assertThat(superCat).isEqualTo(assignedSuperCat);
    }

    @Test
    void assignSuperCat_shouldThrowCatException() {
        Long catId = 1L;
        Long cityId = 1L;

        Mockito.when(catService.getSuperCatId(catId)).thenThrow(new CatException("кот не найден"));

        assertThatThrownBy(() -> cityService.assignSuperCat(catId, cityId))
                .isInstanceOf(CatException.class)
                .hasMessageContaining("кот не найден");
    }

    @Test
    void assignSuperCat_shouldThrowCityException() {
        Long catId = 1L;
        Long cityId = 1L;

        Mockito.when(cityRepository.findById(cityId)).thenThrow(new CityException("город не найден"));

        assertThatThrownBy(() -> cityService.assignSuperCat(catId, cityId))
                .isInstanceOf(CityException.class)
                .hasMessageContaining("город не найден");
    }
}