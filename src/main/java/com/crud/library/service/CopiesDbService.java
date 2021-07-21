package com.crud.library.service;

import com.crud.library.domain.Copies;
import com.crud.library.repository.CopiesDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CopiesDbService {
    private final CopiesDao copiesDao;

    public List<Copies> getAvaliable() {
        return copiesDao.getAvaliable("AVALIABLE");
    }
    public Copies saveCopy(final Copies copies) {
        return copiesDao.save(copies);
    }
}
