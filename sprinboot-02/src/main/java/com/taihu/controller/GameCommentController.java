package com.taihu.controller;

import com.taihu.entity.GuideComment;
import com.taihu.mapper.GameCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gameComment")
public class GameCommentController {
    @Autowired
    private GameCommentMapper gameCommentMapper;

    @GetMapping("/detail/{id}")
    public List<GuideComment> findAllById(@PathVariable("id") Integer id){
        return gameCommentMapper.findAllById(id);
    }
}
