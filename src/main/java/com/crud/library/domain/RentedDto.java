package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class RentedDto {
    private Long id;
    private CopiesDto copyDto;
    private MemberDto memberDto;
    private LocalDate rentedDate;
}
