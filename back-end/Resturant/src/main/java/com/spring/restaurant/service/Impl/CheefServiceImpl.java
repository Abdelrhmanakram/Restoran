package com.spring.restaurant.service.Impl;

import com.spring.restaurant.mapper.CheefMapper;
import com.spring.restaurant.model.Cheef;
import com.spring.restaurant.repository.CheefRepository;
import com.spring.restaurant.service.CheefService;
import com.spring.restaurant.service.dto.CheefDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheefServiceImpl implements CheefService {

    @Autowired
    private CheefRepository cheefRepository;


    @Override
    public List<CheefDto> getAllCheefs() {
        return CheefMapper.CHEEF_MAPPER.toDtoList(cheefRepository.findAll().stream()
                .collect(Collectors.toList()));
    }

}
