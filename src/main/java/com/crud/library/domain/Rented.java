package com.crud.library.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "RENTED")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rented {
    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COPY_ID")
    private Copies copy;
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    @Column(name = "RENTED")
    private LocalDate rentedDate;
    @Column(name = "RETURNED")
    private LocalDate returned;

    public Rented(Copies copy, Member member, LocalDate rentedDate) {
        this.copy = copy;
        this.member = member;
        this.rentedDate = rentedDate;
    }

    public Rented(Long id, Copies copy, Member member, LocalDate rentedDate) {
        this.id = id;
        this.copy = copy;
        this.member = member;
        this.rentedDate = rentedDate;
    }
}
