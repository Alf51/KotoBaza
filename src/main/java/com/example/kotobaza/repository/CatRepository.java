package com.example.kotobaza.repository;

import com.example.kotobaza.modeles.SuperCat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatRepository extends JpaRepository<SuperCat, Long> {
    Optional<SuperCat> findBySuperName(String superName);
}
