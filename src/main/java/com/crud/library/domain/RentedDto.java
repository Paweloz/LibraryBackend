package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class RentedDto {
    private int id;
    private Copies copy;
    private Member member;
    private LocalDate rented;
}
