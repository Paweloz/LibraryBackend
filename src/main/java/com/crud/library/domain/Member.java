package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "LASTNAME")
    private String lastName;
    @Column(name = "CREATION_DATE")
    private LocalDate accountCreationDate;

}
