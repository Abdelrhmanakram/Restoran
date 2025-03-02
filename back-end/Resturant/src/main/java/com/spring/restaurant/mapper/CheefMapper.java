package com.spring.restaurant.mapper;

import com.spring.restaurant.model.Category;
import com.spring.restaurant.model.Cheef;
import com.spring.restaurant.service.dto.CategoryDto;
import com.spring.restaurant.service.dto.CheefDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CheefMapper {

    CheefMapper CHEEF_MAPPER = Mappers.getMapper(CheefMapper.class);

    CheefDto toDto(Cheef cheef);
    List<CheefDto> toDtoList(List<Cheef> cheefList);

    Cheef toEntity(CheefDto cheefDto);
    List<Cheef> toEntityList(List<CheefDto> cheefDtoList);
}
