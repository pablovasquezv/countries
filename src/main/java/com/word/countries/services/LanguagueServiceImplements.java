package com.word.countries.services;

import com.word.countries.ImplemtServices.ILanguagueImplementServices;
import com.word.countries.models.Country;
import com.word.countries.models.Language;
import com.word.countries.repositories.ICountryRepository;
import com.word.countries.repositories.ILanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LanguagueServiceImplements implements ILanguagueImplementServices {
    @Autowired
    private ILanguageRepository iLanguageRepository;
    @Autowired
    private ICountryRepository iCountryRepository;

    @Override
    @Transactional(readOnly = false)
    public Language save(Language language) {
        Country country = iCountryRepository.findById(language.getCountry().getId_country()).orElse(null);
        language.setCountry(country);
        return iLanguageRepository.save(language);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Language> findAll(Sort sort) {
        return iLanguageRepository.findAll(sort);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Language> findAll(Pageable pageable) {
        return iLanguageRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Language findById(long id) {
        return iLanguageRepository.findById(id);
    }

    @Override
    public void delete(long id) {
        iLanguageRepository.deleteById(id);
    }
}
