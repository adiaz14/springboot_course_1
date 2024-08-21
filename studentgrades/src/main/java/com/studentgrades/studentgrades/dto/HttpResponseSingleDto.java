package com.studentgrades.studentgrades.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;


/**
 * Mapping generic http response
 */
@Data
@Component
public class HttpResponseSingleDto<T> {

    private String mssg;
    private boolean success;
    private T data;
    private int total;

    public HttpResponseSingleDto() {
        this.data = null; // Initialize with an empty list by default
        this.total = 0; // Initialize total to 0
    }
}
