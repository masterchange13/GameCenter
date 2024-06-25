package com.taihu.mapper;

import com.taihu.entity.Game;
import com.taihu.entity.RecommendedGame;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface RecommendedGameJpa{
    @Select("select * from games")
    List<RecommendedGame> findAll();
}
