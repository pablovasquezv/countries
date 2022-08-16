package com.word.countries.controllers;

import com.word.countries.ImplemtServices.ILanguagueImplementServices;
import com.word.countries.models.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(value = "word/language")
public class LanguageRestController {
    @Autowired
    private ILanguagueImplementServices iLanguagueImplementServices;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> addNewLanguage(@Valid @RequestBody Language language, BindingResult bindingResult) {
        Map<String, Object> responseAsMap = new HashMap<>();
        ResponseEntity<Map<String, Object>> responseEntity = null;
        List<String> errores = null;
        if (bindingResult.hasErrors()) {
            errores = new ArrayList<String>();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errores.add(error.getDefaultMessage());
            }
            responseAsMap.put("error", errores);
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
        }
        try {
            Language languageFromDB = iLanguagueImplementServices.save(language);
            if (languageFromDB != null) {
                responseAsMap.put("Language:", language);
                responseAsMap.put("Mensaje: ", "El Language con Id: " + language.getId_language() + "¡Sé ha creado Exitosamente!");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.OK);
            } else {
                responseAsMap.put("Mensaje: ", "¡No se creó el Lenguage!");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (DataAccessException e) {
            responseAsMap.put("Mensaje:", "¡No se creó el País" + e.getMostSpecificCause().toString());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
}
