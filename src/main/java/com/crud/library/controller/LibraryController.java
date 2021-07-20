package com.crud.library.controller;

import com.crud.library.domain.*;
import com.crud.library.exception.NoSuchRentalException;
import com.crud.library.mapper.BookMapper;
import com.crud.library.mapper.CopiesMapper;
import com.crud.library.mapper.MemberMapper;
import com.crud.library.mapper.RentedMapper;
import com.crud.library.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/library")
@RequiredArgsConstructor
public class LibraryController {

    private final DbService dbService;
    private final MemberMapper memberMapper;
    private final BookMapper bookMapper;
    private final CopiesMapper copiesMapper;
    private final RentedMapper rentedMapper;

    @PostMapping(value = "createMember")
    public void createMember(@RequestBody MemberDto memberDto) {
        Member member = memberMapper.mapMemberDtoToMember(memberDto);
        dbService.saveMember(member);
    }

    @PostMapping(value = "createTitle")
    public void createTitle(@RequestBody BookDto bookDto) {
        Book book = bookMapper.mapBookDtoToBook(bookDto);
        dbService.saveBook(book);
    }

    @PostMapping(value = "createCopy")
    public void createCopy(@RequestBody CopiesDto copiesDto) {
        Copies copies = copiesMapper.mapCopiesDtoToCopies(copiesDto);
        dbService.saveCopy(copies);
    }

    @PutMapping(value = "changeStatus")
    public CopiesDto changeCopyStatus(@RequestBody CopiesDto copiesDto) {
        Copies copies = copiesMapper.mapCopiesDtoToCopies(copiesDto);
        Copies savedCopy = dbService.saveCopy(copies);
        return copiesMapper.mapCopiesToCopiesDto(savedCopy);
    }

    @GetMapping(value = "getCopiesAvaliable")
    public List<Copies> getCopiesAvaliable() {
        return dbService.getAvaliable();
    }

    @PostMapping(value = "rentBook")
    public void rentBook(@RequestBody RentedDto rentedDto) {
        Rented rented = rentedMapper.mapRentedDtoToRented(rentedDto);
        dbService.saveRental(rented);
    }

    @DeleteMapping(value = "returnBook")
    public void returnBook(@RequestParam int rentId) throws NoSuchRentalException {
        Rented savedRented = dbService.getRental(rentId).orElseThrow(NoSuchRentalException::new);
        savedRented.getCopy().setStatus("AVALIABLE");
        dbService.deleteRental(rentId);
    }

    @GetMapping(value = "getBooks")
    public List<BookDto> getBooks() {
        List<Book> books = dbService.getBooks();
        return bookMapper.mapToBookDtoList(books);
    }

    @GetMapping(value = "getMembers")
    public List<MemberDto> getMembers() {
        List<Member> memberList = dbService.getMembers();
        return memberMapper.mapToMemberDtoList(memberList);
    }
}
