package com.crud.library.domain;


import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "RENTED")
public class Rented {

    private Long id;
    private Copies copy;
    private Member member;
    private LocalDate rented;
    private LocalDate returned;

    public Rented() {}

    public Rented(Long id, Copies copy, Member member, LocalDate rented) {
        this.id = id;
        this.copy = copy;
        this.member = member;
        this.rented = rented;
    }

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    public Long getId() {
        return id;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COPY_ID")
    public Copies getCopy() {
        return copy;
    }

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    public Member getMember() {
        return member;
    }

    @Column(name = "RENTED")
    public LocalDate getRented() {
        return rented;
    }

    @Column(name = "RETURNED")
    public LocalDate getReturned() {
        return returned;
    }

    private void setId(Long id) {
        this.id = id;
    }

    private void setCopy(Copies copy) {
        this.copy = copy;
    }

    private void setMember(Member member) {
        this.member = member;
    }

    private void setRented(LocalDate rented) {
        this.rented = rented;
    }

    public void setReturned(LocalDate returned) {
        this.returned = returned;
    }
}
