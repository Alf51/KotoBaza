package com.example.kotobaza.modeles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.springframework.context.annotation.Lazy;

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
    @SequenceGenerator(name="generator", allocationSize = 1, initialValue = 7)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "population")
    private int population;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    @Lazy
    @Cascade(value = org.hibernate.annotations.CascadeType.PERSIST)
    private List<SuperCat> catList;
}
