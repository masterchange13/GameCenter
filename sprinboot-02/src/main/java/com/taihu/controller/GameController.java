package com.taihu.controller;

import com.taihu.entity.Game;
import com.taihu.mapper.GameJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gameGame")
public class GameController {
   @Autowired
   private GameJpa gameJpa;

    @GetMapping("/detail/{id}")
    public Game findGameById(@PathVariable("id") int id){
        return gameJpa.findGameById(id);
    }
}
