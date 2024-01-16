package com.example.kotobaza.utils.validators;

import com.example.kotobaza.modeles.City;
import com.example.kotobaza.services.CityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CityValidator implements Validator {
    private final CityService cityService;

    @Override
    public boolean supports(Class<?> clazz) {
        return City.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        City city = (City) target;

        Optional<City> existingCity = cityService.getCityByName(city.getName());
        if (existingCity.isEmpty()) {
            return;
        }

        boolean isUpdateForCityInBd = city.getId() != null && existingCity.get().getId().equals(city.getId());
        if (!isUpdateForCityInBd) {
            errors.rejectValue("name", "111", "Кто-то уже основал город с таким названием");
        }
    }
}
