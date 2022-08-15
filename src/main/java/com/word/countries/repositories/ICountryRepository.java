package com.word.countries.repositories;

import com.word.countries.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICountryRepository extends JpaRepository<Country, Long> {
    // Seleccionar todas la instancias desde la BD y haciendo un Join con la tabla
    // stock
    @Query(value = "select c from Country c")
    public List<Country>findAll(Sort sort);

    // Buscar un Country por ID
    @Query(value = "select c from Country c where c.id=:id")
    public Country findById(long id);
}
