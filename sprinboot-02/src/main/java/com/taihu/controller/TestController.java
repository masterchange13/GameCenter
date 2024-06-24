package com.taihu.controller;

import com.taihu.entity.Goods;
import com.taihu.entity.User01;
import com.taihu.mapper.GoodsJpa;
import com.taihu.mapper.UserJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    // 调用数据库操作接口
    @Autowired
    UserJpa userJpa;

    @Autowired
    GoodsJpa goodsJpa;
    //注册用户
    @RequestMapping("/addUser")
    public String addUser(){
        System.out.println("addUser--------------");
        User01 user01 = new User01();
        user01.setUsername("user");
        user01.setPassword("123");
        userJpa.save(user01);
        return "ok";
    }

    // 登录
    @RequestMapping("/login")
    public User01 login(User01 user01){
        System.out.println("login--------------");
        return userJpa.findByUsernameAndPassword(user01.getUsername(), user01.getPassword());
    }

    //查询全部商品
    @RequestMapping("/findeAll")
    public List<Goods> findeAll(){
        System.out.println("findeAll--------------");
        return goodsJpa.findAll();
    }

    //删除商品
    @RequestMapping("/delGoods")
    public List<Goods> delGoods(Integer goodsid){
        System.out.println("delGoods--------------");
        goodsJpa.deleteById(goodsid);
        return goodsJpa.findAll();
    }

    //修改商品
    @RequestMapping("/upGoods")
    public List<Goods> upGoods(Goods goods){
        System.out.println("upGoods--------------");
        goodsJpa.save(goods);
        return goodsJpa.findAll();
    }

    @RequestMapping("/tofindGooods01")
    public List<Goods> tofindGooods01(){

        return goodsJpa.findAll();
    }


}
