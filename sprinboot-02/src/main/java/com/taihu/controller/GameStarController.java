package com.taihu.controller;

import com.taihu.entity.GameStar;
import com.taihu.mapper.GameStarJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gameStar")
public class GameStarController {

    @Autowired
    GameStarJpa gameStarJpa;

    @GetMapping("/findAll")
    public List<GameStar> findAll(){
        return gameStarJpa.findAll();
    }

    @GetMapping("/detail/{id}")
    public GameStar findById(@PathVariable("id") Integer id){

        return gameStarJpa.findById(id);
    }
}
