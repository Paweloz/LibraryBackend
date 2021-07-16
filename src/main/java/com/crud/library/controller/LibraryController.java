package com.crud.library.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/library")
public class LibraryController {

    @PostMapping(value = "createMember")
    public void createMember() {

    }

    @PostMapping(value = "createTitle")
    public void createTitle() {

    }

    @PostMapping(value = "createCopy")
    public void createCopy() {

    }

    @PutMapping(value = "changeStatus")
    public void changeCopyStatus() {

    }

    @GetMapping(value = "getCopiesAvaliable")
    public void getCopiesAvaliable() {

    }

    @PostMapping(value = "rentBook")
    public void rentBook() {

    }

    @DeleteMapping(value = "returnBook")
    public void returnBook() {

    }


}
