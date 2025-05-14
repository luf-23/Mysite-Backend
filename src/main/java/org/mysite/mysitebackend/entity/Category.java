package org.mysite.mysitebackend.entity;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Category {
    private Integer categoryId;
    private Integer  userId;
    private String categoryName;
    private String categoryDescription;
    private LocalDateTime  createTime;
    private LocalDateTime  updateTime;
}
