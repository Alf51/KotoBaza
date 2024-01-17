package com.example.kotobaza.repository;

import com.example.kotobaza.modeles.SuperCat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SuperCatRepository extends JpaRepository<SuperCat, Long> {
    Optional<SuperCat> findBySuperName(String superName);
    List<SuperCat> findSuperCatsByCityIsNull();
}
