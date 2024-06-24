package com.taihu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "game_guides")
public class GameGuide {
    @Id
    private int guideId;
    private String gameName; // 外键关联 games 表
    private String guideContent;

    public void setGuideId(Integer guideId) {
        this.guideId = guideId;
    }

    public Integer getGuideId() {
        return guideId;
    }
}