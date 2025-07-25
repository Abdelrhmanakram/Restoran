package com.spring.restaurant.service.Impl;

import com.spring.restaurant.controller.vm.OrderDetailsVM;
import com.spring.restaurant.mapper.OrdersMapper;
import com.spring.restaurant.mapper.ProductMapper;
import com.spring.restaurant.model.Orders;
import com.spring.restaurant.model.Product;
import com.spring.restaurant.model.userModel.User;
import com.spring.restaurant.repository.OrdersRepository;
import com.spring.restaurant.service.OrdersService;
import com.spring.restaurant.service.ProductService;
import com.spring.restaurant.service.dto.OrdersDto;
import com.spring.restaurant.service.jwt.UserService;
import com.spring.restaurant.util.UserCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Override
    public Map<String, String> saveOrder(OrdersDto ordersDto) {
        List<Product> products = ProductMapper.PRODUCT_MAPPER.toEntityList(productService.findProductsByIds(ordersDto.getProductsIds()));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Orders orders = OrdersMapper.ORDER_MAPPER.toEntity(ordersDto);
        orders.setUser(user);
        orders.setProducts(products);

        String code = generateUniqueCode();
        orders.setCode(code);

        ordersRepository.save(orders);
        Map<String, String> response = new LinkedHashMap<>();
        response.put("code", orders.getCode());
        return response;

    }

    @Override
    public OrderDetailsVM getOrderDetails(String code) throws RuntimeException {
        Optional<Orders> orders = ordersRepository.findByCode(code);

        if (orders.isEmpty()) {
            throw new RuntimeException("error.invalid.orderDetails");
        }
        Orders order = orders.get();

        return extractOrderDetailsVM(order);
    }

    @Override
    public List<OrderDetailsVM> getAllOrderDetails() {
        List<Orders> orders = ordersRepository.findAll();
        return orders.stream().map(order -> {
            OrderDetailsVM orderDetailsVM = extractOrderDetailsVM(order);
            orderDetailsVM.setUserName(order.getUser().getName());
            orderDetailsVM.setEmail(order.getUser().getEmail());
            return orderDetailsVM;
        }).collect(Collectors.toList());
    }

    @Override
    public List<OrderDetailsVM> getUserOrderDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        List<Orders> orders = ordersRepository.findByUserId(user.getId());

        return orders.stream().map(order -> extractOrderDetailsVM(order)).collect(Collectors.toList());    }

    private String generateUniqueCode() {
        String code;
        Optional<Orders> codeExists;

        do {
            code = UserCode.extractCode();
            codeExists = ordersRepository.findByCode(code);
        } while (codeExists.isPresent());

        return code;
    }

    private OrderDetailsVM extractOrderDetailsVM(Orders order) {
        OrderDetailsVM orderDetailsVM = new OrderDetailsVM();
        orderDetailsVM.setCode(order.getCode());
        orderDetailsVM.setTotalPrice(order.getTotalPrice());
        orderDetailsVM.setTotalQuantity(order.getTotalQuantity());
        orderDetailsVM.setProductDtos(ProductMapper.PRODUCT_MAPPER.toDtoList(order.getProducts()));
        return orderDetailsVM;
    }
}
