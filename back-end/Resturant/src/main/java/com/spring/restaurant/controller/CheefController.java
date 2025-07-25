package com.spring.restaurant.controller;

import com.spring.restaurant.model.Cheef;
import com.spring.restaurant.service.CheefService;
import com.spring.restaurant.service.dto.CheefDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cheef")
//@CrossOrigin("http://localhost:4200")
public class CheefController {

    @Autowired
    private CheefService cheefService;

    @GetMapping
    ResponseEntity<List<CheefDto>> cheefs() {
        return  ResponseEntity.ok(cheefService.getAllCheefs());
    }
}
