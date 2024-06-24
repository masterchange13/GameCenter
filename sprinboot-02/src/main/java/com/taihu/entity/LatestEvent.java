package com.taihu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LatestEvent {
    private int eventId;
    private String eventName;
    private Date eventTime;
    private String eventDescription;
    @Id
    private Long id;

}