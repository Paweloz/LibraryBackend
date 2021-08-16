package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String name;
    private String lastName;
    private LocalDate accountCreationDate;
}
