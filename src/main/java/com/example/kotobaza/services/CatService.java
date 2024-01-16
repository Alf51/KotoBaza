package com.example.kotobaza.services;

import com.example.kotobaza.modeles.SuperCat;
import com.example.kotobaza.repository.CatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatService {
    private final CatRepository catRepository;

    public List<SuperCat> getAllSuperCat() {
        return catRepository.findAll();
    }

    public SuperCat getSuperCatId(Long id) {
        return catRepository.findById(id).orElseThrow(() -> new RuntimeException("Кот не найден"));
    }

    public SuperCat save(SuperCat superCat) {
        return catRepository.save(superCat);
    }

    public Optional<SuperCat> getSuperCatBySuperName(String superName) {
        return catRepository.findBySuperName(superName);
    }

    public void deleteById(Long id) {
        catRepository.deleteById(id);
    }
}
