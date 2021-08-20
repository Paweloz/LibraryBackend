package com.crud.library.mapper;

import com.crud.library.domain.Book;
import com.crud.library.domain.dto.BookDto;
import com.crud.library.domain.Copies;
import com.crud.library.repository.CopiesDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookMapper {
    private final CopiesDao copiesDao;

    public Book mapBookDtoToBook(BookDto bookDto) {
        return new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getPublicationYear(),
                bookDto.getCopiesDtoList().stream()
                        .map(copiesDao::findById)
                        .collect(Collectors.toList())
        );
    }

    public BookDto mapBookToBookDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublicationYear(),
                book.getCopiesList().stream()
                        .map(Copies::getId)
                        .collect(Collectors.toList())
        );
    }

    public List<BookDto> mapToBookDtoList(final List<Book> books) {
        return books.stream()
                .map(this::mapBookToBookDto)
                .collect(Collectors.toList());
    }
}
