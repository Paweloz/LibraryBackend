package com.crud.library.controller;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookDto;
import com.crud.library.domain.Copies;
import com.crud.library.domain.CopiesDto;
import com.crud.library.mapper.BookMapper;
import com.crud.library.mapper.CopiesMapper;
import com.crud.library.service.CopiesDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
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
        Copies savedCopy = copiesDbService.saveCopy(copies);
        return copiesMapper.mapCopiesToCopiesDto(savedCopy);
    }

    @PutMapping(value = "changeStatus")
    public CopiesDto changeCopyStatus(@RequestBody CopiesDto copiesDto) {
        Copies copies = copiesMapper.mapCopiesDtoToCopies(copiesDto);
        Copies savedCopy = copiesDbService.saveCopy(copies);
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
        return copiesMapper.mapToCopiesDtoList(copiesDbService.getCopiesAvaliableByBook(bookId));
    }

    @GetMapping(value = "getCopiesAvaliable")
    public List<CopiesDto> getCopiesAvaliable() {
        return copiesMapper.mapToCopiesDtoList(copiesDbService.getAvaliable());
    }

    @DeleteMapping(value = "deleteCopy")
    public void deleteCopy(@RequestParam Long copyId) {
        copiesDbService.deleteCopy(copyId);
    }


}
