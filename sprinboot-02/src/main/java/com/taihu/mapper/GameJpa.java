package com.taihu.mapper;

import com.taihu.entity.Game;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
@Mapper
public interface GameJpa{

    @Select("select * from games where game_name = #{gameName}")
    public Game findByName(String gameName);

    @Select("select * from games where game_id = #{id}")
    Game findGameById(int id);
}
