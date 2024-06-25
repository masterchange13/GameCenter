package com.taihu.controller;

import com.taihu.entity.Game;
import com.taihu.entity.RecommendedGame;
import com.taihu.mapper.RecommendedGameJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gameRecommend")
public class RecommendedGameController {
    @Autowired
    private RecommendedGameJpa recommendedGameJpa;

    @GetMapping("findAll")
    List<RecommendedGame> findAll(){
        return recommendedGameJpa.findAll();
    }
}
