package com.crud.library.controller;

import com.crud.library.domain.Copies;
import com.crud.library.domain.CopiesDto;
import com.crud.library.mapper.CopiesMapper;
import com.crud.library.service.CopiesDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "v1/copies")
public class CopiesController {
    private final CopiesDbService copiesDbService;
    private final CopiesMapper copiesMapper;

    @PostMapping(value = "createCopy")
    public void createCopy(@RequestBody CopiesDto copiesDto) {
        Copies copies = copiesMapper.mapCopiesDtoToCopies(copiesDto);
        copiesDbService.saveCopy(copies);
    }

    @PutMapping(value = "changeStatus")
    public CopiesDto changeCopyStatus(@RequestBody CopiesDto copiesDto) {
        Copies copies = copiesMapper.mapCopiesDtoToCopies(copiesDto);
        Copies savedCopy = copiesDbService.saveCopy(copies);
        return copiesMapper.mapCopiesToCopiesDto(savedCopy);
    }

    @GetMapping(value = "getCopiesAvaliable")
    public List<Copies> getCopiesAvaliable() {
        return copiesDbService.getAvaliable();
    }
}
