package ru.job4j.accident.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "authorities")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Authority {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String authority;
}
