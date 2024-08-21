package com.studentgrades.studentgrades.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;

@Data
public class CourseDto {

    private String name;
    private String mode;
    @Temporal(TemporalType.DATE)
    private Date endDate;

}
