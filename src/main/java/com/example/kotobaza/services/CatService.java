package com.example.kotobaza.services;

import com.example.kotobaza.modeles.SuperCat;
import com.example.kotobaza.repository.SuperCatRepository;
import com.example.kotobaza.utils.exeptions.CatException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatService {
    private final SuperCatRepository superCatRepository;

    public List<SuperCat> getAllSuperCat() {
        return superCatRepository.findAll();
    }

    public SuperCat getSuperCatId(Long id) {
        return superCatRepository.findById(id).orElseThrow(() -> new CatException("Кот не найден"));
    }

    public SuperCat save(SuperCat superCat) {


        return superCatRepository.save(superCat);
    }

    public Optional<SuperCat> getSuperCatBySuperName(String superName) {
        return superCatRepository.findBySuperName(superName);
    }

    public void deleteById(Long id) {
        superCatRepository.deleteById(id);
    }

    public List<SuperCat> getFreeSuperCats() {
        return superCatRepository.findSuperCatsByCityIsNull();
    }
}
