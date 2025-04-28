package com.example.cafeapp.repository;

import com.example.cafeapp.entity.GamePoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamePointRepository extends JpaRepository<GamePoint, Long> {
}
