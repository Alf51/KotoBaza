package com.example.kotobaza.repository;

import com.example.kotobaza.modeles.SuperCat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepository extends JpaRepository<SuperCat, Long> {
}
