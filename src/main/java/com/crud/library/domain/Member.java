package com.crud.library.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MEMBER")
public class Member {

    private int id;
    private String name;
    private String lastName;
    private LocalDate accountCreationDate;
    private List<Rented> rentedBooks = new ArrayList<>();

    public Member() {}

    public Member(int id, String name, String lastName, LocalDate accountCreationDate) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.accountCreationDate = accountCreationDate;
    }

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    public int getId() {
        return id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    @Column(name = "LASTNAME")
    public String getLastName() {
        return lastName;
    }

    @Column(name = "ACCOUNT_CREATION_DATE")
    public LocalDate getAccountCreationDate() {
        return accountCreationDate;
    }

    @OneToMany(
            targetEntity = Rented.class,
            mappedBy = "member",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public List<Rented> getRentedBooks() {
        return rentedBooks;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private void setAccountCreationDate(LocalDate accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    public void setRentedBooks(List<Rented> rentedBooks) {
        this.rentedBooks = rentedBooks;
    }
}
