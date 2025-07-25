package com.spring.restaurant.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Cheef extends BaseEntity{

    @Column(nullable = true)
    private String facebook_link;

    @Column(nullable = true)
    private String instagram_link;

    @Column(nullable = true)
    private String twitter_link;

    @Column(nullable = false)
    private String specialty;
}
