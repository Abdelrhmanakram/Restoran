package com.spring.restaurant.model;

import com.spring.restaurant.model.userModel.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private String totalPrice;
    private String totalQuantity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "request_order_product",
            joinColumns = @JoinColumn(name = "request_order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products ;


    public enum OrderStatus {
        PENDING,
        CONFIRMED,
        REJECTED
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(20) DEFAULT 'PENDING'")
    private OrderStatus status = OrderStatus.PENDING;

}
