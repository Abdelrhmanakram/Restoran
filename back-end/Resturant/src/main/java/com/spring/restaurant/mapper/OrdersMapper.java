package com.spring.restaurant.mapper;

import com.spring.restaurant.model.Orders;
import com.spring.restaurant.service.dto.OrdersDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrdersMapper {

    OrdersMapper ORDER_MAPPER = Mappers.getMapper(OrdersMapper.class);

    Orders toEntity(OrdersDto ordersDto);
    List<Orders> toEntity(List<OrdersDto> ordersDto);

    OrdersDto toDto(Orders orders);
    List<OrdersDto> toDto(List<Orders> orders);
}
