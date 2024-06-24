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
public class GameStar {
    @Id
    private int starId;
    private String starName;
    private String starDescription;
    private String starPhoto;

    public void setStarId(Integer starId) {
        this.starId = starId;
    }

    public Integer getStarId() {
        return starId;
    }
}