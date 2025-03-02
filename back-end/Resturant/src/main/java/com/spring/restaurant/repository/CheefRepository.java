package com.spring.restaurant.repository;

import com.spring.restaurant.model.Category;
import com.spring.restaurant.model.Cheef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheefRepository extends JpaRepository<Cheef, Long> {

}
