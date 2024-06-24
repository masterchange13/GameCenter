package com.taihu.mapper;

import com.taihu.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsJpa extends JpaRepository<Goods,Integer> {
}
