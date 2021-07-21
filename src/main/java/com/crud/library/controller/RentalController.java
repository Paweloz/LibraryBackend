package com.crud.library.controller;

import com.crud.library.domain.Rented;
import com.crud.library.domain.RentedDto;
import com.crud.library.exception.NoSuchRentalException;
import com.crud.library.mapper.RentedMapper;
import com.crud.library.service.RentalDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "v1/rental")
public class RentalController {
    private final RentalDbService rentalDbService;
    private final RentedMapper rentedMapper;

    @PostMapping(value = "rentBook")
    public void rentBook(@RequestBody RentedDto rentedDto) {
        Rented rented = rentedMapper.mapRentedDtoToRented(rentedDto);
        rentalDbService.saveRental(rented);
    }

    @DeleteMapping(value = "returnBook")
    public void returnBook(@RequestParam int rentId) throws NoSuchRentalException {
        Rented savedRented = rentalDbService.getRental(rentId).orElseThrow(NoSuchRentalException::new);
        savedRented.getCopy().setStatus("AVALIABLE");
        rentalDbService.deleteRental(rentId);
    }
}
