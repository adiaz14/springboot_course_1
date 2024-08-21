package com.studentgrades.studentgrades.dto;

import lombok.Data;

@Data
public class TopicDto {

    private int id;
    private String name;
    private String description;
    private int fkCourse;

}
