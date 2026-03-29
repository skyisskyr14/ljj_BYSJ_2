package com.cloudyearbook.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("tag")
public class Tag {
    private Long id;
    private String tagName;
    private String type;
    private LocalDateTime createTime;
}
