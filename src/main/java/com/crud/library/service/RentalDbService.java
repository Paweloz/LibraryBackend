package com.crud.library.service;

import com.crud.library.domain.Rented;
import com.crud.library.repository.RentedDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalDbService {
    private final RentedDao rentedDao;

    public Optional<Rented> getRental(final int rentId) {
        return rentedDao.findById(rentId);
    }
    public Rented saveRental(final Rented rented) {
        rented.getCopy().setStatus("TAKEN");
        return rentedDao.save(rented);
    }
    public void deleteRental(final int rentId) {
        rentedDao.deleteById(rentId);
    }
}
