package com.word.countries.controllers;

import com.word.countries.ImplemtServices.ICountryImplementServices;
import com.word.countries.models.Country;
import org.hibernate.cache.spi.DirectAccessRegion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "word/country")
public class CountryRestController {
    @Autowired
    private ICountryImplementServices iCountryImplementServices;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> addNewCountry(@Valid @RequestBody Country country, BindingResult bindingResult) {
        Map<String, Object> responseAsMap = new HashMap<String, Object>();
        ResponseEntity<Map<String, Object>> responseEntity = null;
        List<String> errores = null;
        if (bindingResult.hasErrors()) {
            errores = new ArrayList<String>();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errores.add(error.getDefaultMessage());
            }
            responseAsMap.put("errors", errores);
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
        }
        try {
            Country countryFromBD = iCountryImplementServices.save(country);
            if (countryFromBD != null) {
                responseAsMap.put("Country", country);
                responseAsMap.put("Mensaje: ", "El País con ID: " + country.getId_country() + "¡Sé ha creado Exitosamente!");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.OK);
            } else {
                responseAsMap.put("Mensaje: ", "¡No se creó el País!");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (DataAccessException e) {
            responseAsMap.put("Mensaje:", "¡No se creó el País" + e.getMostSpecificCause().toString());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @GetMapping(value = "get/all")
    public ResponseEntity<List<Country>> findAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        Sort sortByName = Sort.by("name_country");
        //Creación de
        ResponseEntity<List<Country>> responseEntity = null;
        //Creación de una lista inciada en vacio
        List<Country> countries = null;
        if (page != null && size != null) {
            Pageable pageable = PageRequest.of(page, size, sortByName);
            countries = iCountryImplementServices.findAll(pageable).getContent();
        } else {
            countries = iCountryImplementServices.findAll(sortByName);
        }
        //Si tiene Paises la lista
        if (countries.size() > 0) {
            // retorna un 200
            responseEntity = new ResponseEntity<List<Country>>(countries, HttpStatus.OK);
        } else {
            // retorna un 204
            responseEntity = new ResponseEntity<List<Country>>(countries, HttpStatus.NO_CONTENT);
        }
        return responseEntity;
    }
}
