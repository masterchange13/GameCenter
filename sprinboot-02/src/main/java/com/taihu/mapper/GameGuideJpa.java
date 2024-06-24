package com.taihu.mapper;

import com.taihu.entity.GameGuide;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
//@Mapper
//public interface GameGuideJpa extends JpaRepository<GameGuide, Integer> {
//    @Select("select * from game_guides")
//    List<GameGuide> findAll();
//}

@Mapper
public interface GameGuideJpa{
    @Select("select * from game_guides")
    List<GameGuide> findAll();

    @Select("select * from game_guides where guide_id = #{id}")
    GameGuide findById(Integer id);
}