package com.spring.restaurant.model.userModel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "clients")
public class User extends  UserBaseEntity{

    private String name;
    private String email;
    private String phoneNumber;
    private String password;

//    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
//    private List<Orders> requestOrders;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

//    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
//    private List<ContactInfo> contactInfo;

}
