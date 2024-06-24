package com.taihu.mapper;

import com.taihu.entity.User01;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Mapper
public interface UserJpa extends JpaRepository<User01,Integer> {
    // 条件查询


   public User01 findByUsernameAndPassword(String username,String password);
}
