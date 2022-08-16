package com.word.countries.services;

import com.word.countries.ImplemtServices.ICityImplementServices;
import com.word.countries.models.City;
import com.word.countries.models.Country;
import com.word.countries.repositories.ICityRepositories;
import com.word.countries.repositories.ICountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityServicesImplements implements ICityImplementServices {
    @Autowired
    private ICityRepositories iCityRepositories;
    @Autowired
    private ICountryRepository iCountryRepository;

    @Override
    @Transactional(readOnly = false)
    public City save(City city) {
        Country country = iCountryRepository.findById(city.getCountry().getId_country()).orElse(null);
        city.setCountry(country);
        return iCityRepositories.save(city);
    }

    @Override
    @Transactional(readOnly = true)
    public List<City> findAll(Sort sort) {
        return iCityRepositories.findAll(sort);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<City> findAll(Pageable pageable) {
        return iCityRepositories.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public City findById(long id) {
        return iCityRepositories.findById(id);
    }

    @Override
    public void delete(long id) {
        iCityRepositories.deleteById(id);
    }
}
