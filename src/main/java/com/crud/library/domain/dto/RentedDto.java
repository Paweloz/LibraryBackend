package com.crud.library.domain.dto;

import com.crud.library.domain.dto.CopiesDto;
import com.crud.library.domain.dto.MemberDto;
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
