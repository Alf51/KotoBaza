package com.example.kotobaza.services;

import com.example.kotobaza.modeles.City;
import com.example.kotobaza.modeles.SuperCat;
import com.example.kotobaza.repository.CityRepository;
import com.example.kotobaza.utils.exeptions.CatException;
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
    private SuperCat cat;
    private City city;

    @Mock
    private CatService catService;

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    @BeforeEach
    void initData() {
        cat = new SuperCat();
        cat.setId(1L);
        cat.setAge(3);
        cat.setSuperName("Кошачий глаз");
        cat.setName("Барсик");

        city = new City();
        city.setId(1L);
        city.setName("Котоярск");
        city.setPopulation(2000);
    }

    @Test
    void whenAssignSuperCatThenCityHaveCat() {
        Mockito.when(catService.getSuperCatId(1L)).thenReturn(cat);
        Mockito.when(cityRepository.findById(1L)).thenReturn(Optional.of(city));

        cityService.assignSuperCat(cat.getId(), city.getId());
        var assignedSuperCats = city.getCats();

        assertThat(assignedSuperCats).contains(cat);
        assertThat(assignedSuperCats.get(0)).isEqualTo(cat);
    }

    @Test
    void whenAssignSuperCatThenCityHaveNotCat() {
        Mockito.when(catService.getSuperCatId(1L)).thenThrow(new CatException("кот не найден"));

        assertThatThrownBy(() -> cityService.assignSuperCat(cat.getId(), city.getId()))
                .isInstanceOf(CatException.class)
                .hasMessageContaining("кот не найден");

        assertThat(city.getCats()).isNull();
    }
}