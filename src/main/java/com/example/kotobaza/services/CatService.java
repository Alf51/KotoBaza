package com.example.kotobaza.services;

import com.example.kotobaza.modeles.SuperCat;
import com.example.kotobaza.repository.CatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatService {
    private final CatRepository catRepository;

    public List<SuperCat> getAllSuperCat() {
        return catRepository.findAll();
    }

    public SuperCat getSuperCat(Long id) {
        return catRepository.findById(id).orElseThrow(() -> new RuntimeException("Кот не найден"));
    }
}
