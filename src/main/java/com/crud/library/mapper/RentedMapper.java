package com.crud.library.mapper;

import com.crud.library.domain.Rented;
import com.crud.library.domain.RentedDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentedMapper {

    public RentedDto mapRentedToRentedDto(Rented rented) {
        return new RentedDto(
                rented.getId(),
                rented.getCopy(),
                rented.getMember(),
                rented.getRented()
        );
    }

    public Rented mapRentedDtoToRented( RentedDto rentedDto ) {
        return new Rented(
                rentedDto.getId(),
                rentedDto.getCopy(),
                rentedDto.getMember(),
                rentedDto.getRented()
        );
    }

    public List<RentedDto> mapToRentedDtoList( List<Rented> rentedList ) {
        return rentedList.stream()
                .map(this::mapRentedToRentedDto)
                .collect(Collectors.toList());
    }
}
