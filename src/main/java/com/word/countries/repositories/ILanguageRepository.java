package com.word.countries.repositories;

import com.word.countries.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILanguageRepository extends JpaRepository<Language, Long> {
}
