package com.crud.library.mapper;

import com.crud.library.domain.Rented;
import com.crud.library.domain.RentedDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentedMapper {
    private final CopiesMapper copiesMapper;
    private final MemberMapper memberMapper;

    public RentedDto mapRentedToRentedDto(Rented rented) {
        return new RentedDto(
                rented.getId(),
                copiesMapper.mapCopiesToCopiesDto(rented.getCopy()),
                memberMapper.mapMemberToMemberDto(rented.getMember()),
                rented.getRentedDate()
        );
    }

    public Rented mapRentedDtoToRented( RentedDto rentedDto ) {
        return new Rented(
                rentedDto.getId(),
                copiesMapper.mapCopiesDtoToCopies(rentedDto.getCopyDto()),
                memberMapper.mapMemberDtoToMember(rentedDto.getMemberDto()),
                rentedDto.getRentedDate()
        );
    }

    public List<RentedDto> mapToRentedDtoList( List<Rented> rentedList ) {
        return rentedList.stream()
                .map(this::mapRentedToRentedDto)
                .collect(Collectors.toList());
    }
}
