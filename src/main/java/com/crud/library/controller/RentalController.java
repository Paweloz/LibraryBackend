package com.crud.library.controller;

import com.crud.library.domain.Rented;
import com.crud.library.domain.RentedDto;
import com.crud.library.exception.NoSuchRentalException;
import com.crud.library.mapper.RentedMapper;
import com.crud.library.service.RentalDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
        Rented savedRented = rentalDbService.saveRental(rented);
        return rentedMapper.mapRentedToRentedDto(savedRented);
    }

    @GetMapping(value = "getRented")
    public List<RentedDto> getRented() {
        List<Rented> rentedList = rentalDbService.getRented();
        return rentedMapper.mapToRentedDtoList(rentedList);
    }

    @GetMapping(value = "getRentedByMemberLastname")
    public List<RentedDto> getRentedByMemberLastname(@RequestParam String lastname) {
        List<Rented> rentedList = rentalDbService.getRentedByMemberLastname(lastname);
        return rentedMapper.mapToRentedDtoList(rentedList);
    }

    @PutMapping(value = "returnBook")
    public void returnBook(@RequestParam Long rentId) {
        rentalDbService.deleteRental(rentId);
    }
}
