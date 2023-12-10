package com.lms.pharmacyrecommend.direction.repository;

import com.lms.pharmacyrecommend.direction.entity.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectionRepository extends JpaRepository<Direction, Long> {
}
