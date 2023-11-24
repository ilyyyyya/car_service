package com.carService.product.repos;

import com.carService.product.entity.AutoPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface AutopartRepo extends JpaRepository<AutoPart, Long> {
}
