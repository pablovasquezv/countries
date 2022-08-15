package com.word.countries.services;

import com.word.countries.ImplemtServices.ICountryImplementServices;
import com.word.countries.models.Country;
import com.word.countries.repositories.ICountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryServices implements ICountryImplementServices {

    @Autowired
    private ICountryRepository iCountryRepository;

    @Override
    @Transactional(readOnly = false)
    public Country save(Country country) {
        return iCountryRepository.save(country);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Country> findAll(Sort sort) {
        return iCountryRepository.findAll(sort);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Country> findAll(Pageable pageable) {
        return iCountryRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Country findById(long id) {
        return iCountryRepository.findById(id);
    }

    @Override
    public void delete(long id) {
        iCountryRepository.deleteById(id);
    }
}
