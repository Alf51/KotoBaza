package com.example.kotobaza.modeles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "CITIES")
@Getter
@Setter
@NoArgsConstructor
public class City {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @SequenceGenerator(name="generator", allocationSize = 1, initialValue = 6)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "population")
    private int population;

    @OneToMany(mappedBy = "city")
    private List<SuperCat> catList;
}
