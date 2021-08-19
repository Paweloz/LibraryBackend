package com.crud.library.service;

import com.crud.library.common.Validator;
import com.crud.library.domain.Copies;
import com.crud.library.domain.Rented;
import com.crud.library.repository.CopiesDao;
import com.crud.library.repository.RentedDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalDbService {
    private final RentedDao rentedDao;
    private final CopiesDao copiesDao;
    private final Validator validator;
    private static final String avaliableCopyStatus = "AVALIABLE";
    private static final String takenCopyStatus = "TAKEN";


    public Rented getRentedById(final Long rentId) {
        validator.valdiateRentalById( rentId );
        return rentedDao.findById(rentId);
    }

    public List<Rented> getRentedByMemberLastname(final String lastname) {
        validator.validateRentalByLastName(lastname);
        return rentedDao.findByMember_LastName(lastname);
    }
    public List<Rented> getRented() {
        return rentedDao.findAll();
    }

    public Rented saveRental(final Rented rented) {
        validator.validateRental(rented);
        Rented savedRented = rentedDao.save(rented);
        Copies rentedCopy = savedRented.getCopy();
        rentedCopy.setStatus(takenCopyStatus);
        copiesDao.save(rentedCopy);
        return savedRented;
    }
    public void returnRental(final Long rentId) {
        validator.valdiateRentalById(rentId);
        Rented rented = getRentedById(rentId);
        rented.setReturned(LocalDate.now());
        rented.getCopy().setStatus(avaliableCopyStatus);
        rentedDao.save(rented);
    }
}
