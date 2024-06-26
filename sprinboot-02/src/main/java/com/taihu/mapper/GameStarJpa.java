package com.taihu.mapper;

import com.taihu.entity.GameStar;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GameStarJpa{
    @Select("select * from game_stars")
    List<GameStar> findAll();

    @Select("select * from game_stars where star_id = #{id}")
    GameStar findById(Integer id);

    @Insert("INSERT INTO game_stars (star_name, star_description) VALUES (#{starName}, #{starDescription})")
    void save(GameStar gameStar);

    @Delete("delete from game_stars where star_id = #{id}")
    void deleteById(Integer id);
}
