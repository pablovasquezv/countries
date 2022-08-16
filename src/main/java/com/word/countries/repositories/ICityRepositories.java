package com.word.countries.repositories;

import com.word.countries.models.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ICityRepositories extends JpaRepository<City, Long> {
    // Seleccionar todas la instancias desde la BD y haciendo un Join con la tabla Contry

    @Query(value = "select c from City c left join fetch c.country")
    public List<City> findAll(Sort sort);

    // Contar la cantidad de Lenguajes que vienen en la búsqueda y paginarlos
    @Query(value = "select c from City c left join fetch c.country",
            countQuery = "select (c) from City c left join c.country")
    public Page<City> findAll(Pageable pageable);

    // Buscar por ID
    @Query(value = "select c from City c left join fetch c.country where c.id=:id")
    public City findById(long id);
}
