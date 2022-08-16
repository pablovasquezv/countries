package com.word.countries.controllers;

import com.word.countries.ImplemtServices.ICityImplementServices;
import com.word.countries.models.City;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "word/city")
public class CityRestController {
    @Autowired
    private ICityImplementServices iCityImplementServices;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> addNewCity(@Valid @RequestBody City city, BindingResult bindingResult) {
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
            City cityFromDB = iCityImplementServices.save(city);
            if (cityFromDB != null) {
                responseAsMap.put("Ciudad", city);
                responseAsMap.put("Mensaje:", "La ciudad con Id: " + city.getId_city() + "¡Sé ha creado Exitosamente!");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.OK);

            } else {
                responseAsMap.put("Mensaje: ", "¡No se creó la Ciudad!");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (DataAccessException e) {
            responseAsMap.put("Mensaje:", "¡No se creó el País" + e.getMostSpecificCause().toString());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
