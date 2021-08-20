package com.crud.library.domain.dto;

import com.crud.library.domain.dto.BookDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CopiesDto {
    private Long id;
    private BookDto bookDto;
    private String status;
}
