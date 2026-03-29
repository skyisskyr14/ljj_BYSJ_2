package com.cloudyearbook.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("`class`")
public class ClassEntity {
    private Long id;
    private String className;
    private String grade;
    private String description;
    private Long creatorId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
