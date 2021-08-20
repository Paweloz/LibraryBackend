package com.crud.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String name;
    private String lastName;
    private LocalDate accountCreationDate;
    private List<Long> rentedBooksId;
}
