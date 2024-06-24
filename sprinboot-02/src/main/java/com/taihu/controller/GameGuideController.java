package com.taihu.controller;

import com.taihu.entity.GameGuide;
import com.taihu.mapper.GameGuideJpa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gameGuide")
public class GameGuideController {
    private static final Logger log = LoggerFactory.getLogger(GameGuideController.class);
    @Autowired
    GameGuideJpa gameGuideJpa;
//
//    @GetMapping("/gameGuideAll")
//    public List<GameGuide> getGameGuideAll(){
//        return gameGuideJpa.getGameGuideAll();
//    }

    @GetMapping("/findAll")
    public List<GameGuide> getGameGuideAll(){
        System.out.println("searching-------------------------------------------");
        return gameGuideJpa.findAll();
    }

    @GetMapping("/detail/{id}")
    public GameGuide getGameGuideDetail(@PathVariable("id") Integer id){
        System.out.println("id-------------------------------------------" + gameGuideJpa.findById(id));
       return gameGuideJpa.findById(id);
    }
}
