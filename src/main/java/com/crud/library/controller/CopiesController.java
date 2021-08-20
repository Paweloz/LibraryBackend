package com.crud.library.controller;

import com.crud.library.domain.Book;
import com.crud.library.domain.dto.BookDto;
import com.crud.library.domain.Copies;
import com.crud.library.domain.dto.CopiesDto;
import com.crud.library.exception.BookNotFoundException;
import com.crud.library.exception.CopyNotFoundException;
import com.crud.library.mapper.BookMapper;
import com.crud.library.mapper.CopiesMapper;
import com.crud.library.service.CopiesDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "v1/library/copies")
public class CopiesController {
    private final CopiesDbService copiesDbService;
    private final CopiesMapper copiesMapper;
    private final BookMapper bookMapper;

    @PostMapping(value = "createCopy")
    public CopiesDto createCopy(@RequestBody CopiesDto copiesDto) {
        Copies copies = copiesMapper.mapCopiesDtoToCopies(copiesDto);
        Copies savedCopy;
        try {
            savedCopy = copiesDbService.saveCopy(copies);
        }catch (BookNotFoundException bnf) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, bnf.getMessage());
        }

        return copiesMapper.mapCopiesToCopiesDto(savedCopy);
    }

    @PutMapping(value = "changeStatus")
    public CopiesDto changeCopyStatus(@RequestBody CopiesDto copiesDto) {
        Copies copies = copiesMapper.mapCopiesDtoToCopies(copiesDto);
        Copies savedCopy;
        try {
            savedCopy = copiesDbService.saveCopy(copies);
        }catch (BookNotFoundException bnf) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, bnf.getMessage());
        }
        return copiesMapper.mapCopiesToCopiesDto(savedCopy);
    }

    @GetMapping(value = "getCopiesByBook")
    public List<CopiesDto> getCopiesByBook(@RequestBody BookDto bookDto) {
        Book bookToGet = bookMapper.mapBookDtoToBook(bookDto);
        return copiesMapper.mapToCopiesDtoList(copiesDbService.getCopiesByBook(bookToGet));
    }

    @GetMapping(value = "getAllCopies")
    public List<CopiesDto> getAllCopies() {
        return copiesMapper.mapToCopiesDtoList(copiesDbService.getAllCopies());
    }

    @GetMapping(value = "getCopiesAvaliableByBook")
    public List<CopiesDto> getCopiesAvaliableByBook(@RequestParam Long bookId) {
        List<Copies> copiesList;
        try {
            copiesList = copiesDbService.getCopiesAvaliableByBook(bookId);
        }catch ( BookNotFoundException bnf ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, bnf.getMessage());
        }
        return copiesMapper.mapToCopiesDtoList(copiesList);
    }

    @GetMapping(value = "getCopiesAvaliable")
    public List<CopiesDto> getCopiesAvaliable() {
        return copiesMapper.mapToCopiesDtoList(copiesDbService.getAvaliable());
    }

    @DeleteMapping(value = "deleteCopy")
    public void deleteCopy(@RequestParam Long copyId) {
        try {
            copiesDbService.deleteCopy(copyId);
        }catch (CopyNotFoundException cnf) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, cnf.getMessage());
        }
    }


}
