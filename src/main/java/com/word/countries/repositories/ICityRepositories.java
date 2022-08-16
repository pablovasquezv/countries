package com.word.countries.repositories;

import com.word.countries.models.City;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICityRepositories extends JpaRepository<City, Long> {

}
