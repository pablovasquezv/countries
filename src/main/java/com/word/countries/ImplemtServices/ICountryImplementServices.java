package com.word.countries.ImplemtServices;

import com.word.countries.models.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ICountryImplementServices {

    public Country save(Country country);

    public List<Country> findAll(Sort sort);

    public Page<Country> findAll(Pageable pageable);

    public Country findById(long id);

    public void delete(long id);


}
