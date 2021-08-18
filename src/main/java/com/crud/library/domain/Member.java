package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MEMBER")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Member {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "LASTNAME")
    private String lastName;
    @Column(name = "ACCOUNT_CREATION_DATE")
    private LocalDate accountCreationDate;
    @OneToMany(
            targetEntity = Rented.class,
            mappedBy = "member",
            fetch = FetchType.LAZY
    )
    private List<Rented> rentedBooks = new ArrayList<>();

    public Member(String name, String lastName, LocalDate accountCreationDate) {
        this.name = name;
        this.lastName = lastName;
        this.accountCreationDate = accountCreationDate;
    }
}
