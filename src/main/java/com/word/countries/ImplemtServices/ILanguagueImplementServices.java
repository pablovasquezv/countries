package com.word.countries.ImplemtServices;

import com.word.countries.models.Language;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ILanguagueImplementServices {

    public Language save(Language language);

    public List<Language> findAll(Sort sort);

    public Page<Language> findAll(Pageable pageable);

    public Language findById(long id);

    public void delete(long id);
}
