package com.crud.library.service;

import com.crud.library.domain.Rented;
import com.crud.library.exception.NoSuchRentalException;
import com.crud.library.repository.RentedDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalDbService {
    private final RentedDao rentedDao;
    private static final String avaliableCopyStatus = "AVALIABLE";
    private static final String takenCopyStatus = "TAKEN";


    public Optional<Rented> getRentedById(final int rentId) {
        return rentedDao.findById(rentId);
    }

    public List<Rented> getRentedByMemberLastname(final String lastname) {
        return rentedDao.findByMember_LastName(lastname);
    }
    public List<Rented> getRented() {
        return rentedDao.findAll();
    }

    public Rented saveRental(final Rented rented) {
        rented.getCopy().setStatus(takenCopyStatus);
        return rentedDao.save(rented);
    }
    public void deleteRental(final int rentId) throws NoSuchRentalException {
        Optional<Rented> optionalRented = getRentedById(rentId);
        Rented rented = optionalRented.orElseThrow(NoSuchRentalException::new);
        rented.getCopy().setStatus(avaliableCopyStatus);
        rentedDao.deleteById(rentId);
    }
}
