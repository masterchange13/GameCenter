package com.taihu.mapper;

import com.taihu.entity.RecommendedGamesStar;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Mapper
public interface RecommendedGamesStarJpa extends JpaRepository<RecommendedGamesStar, Long> {
}
