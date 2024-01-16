package com.example.kotobaza.modeles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CATS")
@Getter
@Setter
@NoArgsConstructor
public class SuperCat {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @SequenceGenerator(name="generator", allocationSize = 1, initialValue = 6)
    private Long id;

    @Column(name = "super_name", unique = true)
    private String superName;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @ManyToOne
    @JoinColumn(name = "id_city", referencedColumnName = "id")
    private City city;
}
