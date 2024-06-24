package com.taihu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RecommendedGamesStar {
    @Id
    private int recommendationId; // 外键关联 recommended_games 表
    private int starId; // 外键关联 game_stars 表

    public void setRecommendationId(Integer recommendationId) {
        this.recommendationId = recommendationId;
    }

    public Integer getRecommendationId() {
        return recommendationId;
    }
}