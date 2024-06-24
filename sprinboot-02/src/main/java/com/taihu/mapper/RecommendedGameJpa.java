package com.taihu.mapper;

import com.taihu.entity.RecommendedGame;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Mapper
public interface RecommendedGameJpa extends JpaRepository<RecommendedGame, Long> {
}
