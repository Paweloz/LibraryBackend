package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rented {
    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "COPY_ID", unique = true)
    private int idCopy;
    @Column(name = "MEMBER_ID")
    private int idMember;
    @Column(name = "RENTED_DATE")
    private LocalDate rented;
    @Column(name = "RETURNED_DATE")
    private LocalDate returned;

}
