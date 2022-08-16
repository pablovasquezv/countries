package com.word.countries.repositories;

import com.word.countries.models.Language;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILanguageRepository extends JpaRepository<Language, Long> {
    // Seleccionar todas la instancias desde la BD y haciendo un Join con la tabla
    @Query(value = "select l from Language l left join fetch l.country")
    public List<Language> findAll(Sort sort);

    // Contar la cantidad de Lenguajes que vienen en la búsqueda y paginarlos
    @Query(value = "select l from Language l left join fetch l.country",
            countQuery = "select count(l) from Language l left join l.country")

    public Page<Language> findAll(Pageable pageable);

    // Buscar por ID
    @Query(value = "select l from Language l left join fetch l.country where l.id=:id")
    public Language findById(long id);
}
