package com.taihu.mapper;

import com.taihu.entity.RecommendedGamesEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendedGamesEventJpa extends JpaRepository<RecommendedGamesEvent, Integer> {
}
