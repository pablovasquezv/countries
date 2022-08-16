package com.word.countries.ImplemtServices;

import com.word.countries.models.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ICityImplementServices {

    public City save(City city);

    public List<City> findAll(Sort sort);

    public Page<City> findAll(Pageable pageable);

    public City findById(long id);

    public void delete(long id);

}
