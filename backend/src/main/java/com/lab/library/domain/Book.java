package com.lab.library.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Entity(name = "book")
@Setter
@Getter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    private Integer availableCopies;
    @ManyToOne
    private Author author;
    private Category category;
}
