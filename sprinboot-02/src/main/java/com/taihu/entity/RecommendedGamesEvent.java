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
public class RecommendedGamesEvent {
    private int recommendationId; // 外键关联 recommended_games 表
    @Id
    private Integer eventId; // 外键关联 latest_events 表

}