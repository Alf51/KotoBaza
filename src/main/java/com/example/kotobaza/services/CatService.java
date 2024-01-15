package com.example.kotobaza.services;

import com.example.kotobaza.modeles.SuperCat;
import com.example.kotobaza.repository.CatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CatService {
    private final CatRepository catRepository;

    public List<SuperCat> getAllSuperCat() {
        return catRepository.findAll();
    }
}
