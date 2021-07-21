package com.crud.library.mapper;

import com.crud.library.domain.Copies;
import com.crud.library.domain.CopiesDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CopiesMapper {

    public CopiesDto mapCopiesToCopiesDto(Copies copies) {
        return new CopiesDto(
                copies.getId(),
                copies.getBook(),
                copies.getStatus()
        );
    }

    public Copies mapCopiesDtoToCopies(CopiesDto copiesDto) {
        return new Copies(
                copiesDto.getId(),
                copiesDto.getBook(),
                copiesDto.getStatus()
        );
    }

    public List<CopiesDto> mapToCopiesDtoList(final List<Copies> copiesList) {
        return copiesList.stream()
                .map(this::mapCopiesToCopiesDto)
                .collect(Collectors.toList());
    }
}
