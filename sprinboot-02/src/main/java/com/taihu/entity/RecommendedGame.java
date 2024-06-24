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
public class RecommendedGame {
    @Id
    private int recommendationId;
    private String gameName;
    private String gameDescription;
    private int gameId; // 外键关联 games 表

    public void setRecommendationId(Integer recommendationId) {
        this.recommendationId = recommendationId;
    }

    public Integer getRecommendationId() {
        return recommendationId;
    }
}