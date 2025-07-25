package com.spring.restaurant.model.userModel;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Role extends UserBaseEntity {

    private String code;

    @ManyToMany(mappedBy = "roles")
    private List<User> user;
}
