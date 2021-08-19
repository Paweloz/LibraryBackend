package com.crud.library.controller;

import com.crud.library.domain.Rented;
import com.crud.library.domain.RentedDto;
import com.crud.library.exception.CopyNotFoundException;
import com.crud.library.exception.MemberNotFoundException;
import com.crud.library.exception.RentalNotFoundException;
import com.crud.library.mapper.RentedMapper;
import com.crud.library.service.RentalDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "v1/library/rental")
public class RentalController {
    private final RentalDbService rentalDbService;
    private final RentedMapper rentedMapper;

    @PostMapping(value = "rentBook")
    public RentedDto rentBook(@RequestBody RentedDto rentedDto) {
        Rented rented = rentedMapper.mapRentedDtoToRented(rentedDto);
        Rented savedRented;
        try {
            savedRented = rentalDbService.saveRental(rented);
        } catch ( MemberNotFoundException | CopyNotFoundException mnf ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, mnf.getMessage());
        }
        return rentedMapper.mapRentedToRentedDto(savedRented);
    }

    @GetMapping(value = "getRented")
    public List<RentedDto> getRented() {
        List<Rented> rentedList = rentalDbService.getRented();
        return rentedMapper.mapToRentedDtoList(rentedList);
    }

    @GetMapping(value = "getRentedByMemberLastname")
    public List<RentedDto> getRentedByMemberLastname(@RequestParam String lastname) {
        List<Rented> rentedList;
        try {
            rentedList = rentalDbService.getRentedByMemberLastname(lastname);
        } catch ( RentalNotFoundException rnf ) {
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, rnf.getMessage() );
        }
        return rentedMapper.mapToRentedDtoList(rentedList);
    }

    @PutMapping(value = "returnBook")
    public void returnBook(@RequestParam Long rentId) {
        try {
            rentalDbService.returnRental(rentId);
        } catch ( RentalNotFoundException rnf ) {
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, rnf.getMessage() );
        }
    }
}
