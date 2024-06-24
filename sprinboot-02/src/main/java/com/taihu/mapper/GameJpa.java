package com.taihu.mapper;

import com.taihu.entity.Game;
import org.apache.ibatis.annotations.Mapper;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface GameJpa extends JpaRepository<Game, Integer> {
    public Game findByName(String gameName);
}
