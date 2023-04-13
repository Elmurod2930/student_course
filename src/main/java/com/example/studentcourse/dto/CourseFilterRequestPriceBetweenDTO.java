package com.example.studentcourse.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseFilterRequestPriceBetweenDTO {
    private Integer fromPrice;
    private Integer toPrice;
}
