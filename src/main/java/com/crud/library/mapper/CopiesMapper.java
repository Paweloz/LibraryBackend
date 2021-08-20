package com.crud.library.mapper;

import com.crud.library.domain.Copies;
import com.crud.library.domain.dto.CopiesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CopiesMapper {

    private final BookMapper bookMapper;

    public CopiesDto mapCopiesToCopiesDto(Copies copies) {
        return new CopiesDto(
                copies.getId(),
                bookMapper.mapBookToBookDto(copies.getBook()),
                copies.getStatus()
        );
    }

    public Copies mapCopiesDtoToCopies(CopiesDto copiesDto) {
        return new Copies(
                copiesDto.getId(),
                bookMapper.mapBookDtoToBook(copiesDto.getBookDto()),
                copiesDto.getStatus()
        );
    }

    public List<CopiesDto> mapToCopiesDtoList(final List<Copies> copiesList) {
        return copiesList.stream()
                .map(this::mapCopiesToCopiesDto)
                .collect(Collectors.toList());
    }
}
