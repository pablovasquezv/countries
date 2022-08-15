package com.word.countries.repositories;

import com.word.countries.models.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICityRepositories extends JpaRepository<City, Long> {
}
