package com.example.kotobaza.utils.validators;

import com.example.kotobaza.modeles.SuperCat;
import com.example.kotobaza.services.CatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CatValidator implements Validator {
    private final CatService catService;

    @Override
    public boolean supports(Class<?> clazz) {
        return SuperCat.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SuperCat cat = (SuperCat) target;

        Optional<SuperCat> existingSuperCat = catService.getSuperCatBySuperName(cat.getSuperName());
        if (existingSuperCat.isEmpty()) {
            return;
        }

        boolean isUpdateForSuperCatInBd = cat.getId() != null && existingSuperCat.get().getId().equals(cat.getId());
        if (!isUpdateForSuperCatInBd) {
            errors.rejectValue("superName", "111", "Какой-то котяра уже занял указанное супер имя");
        }

    }
}
