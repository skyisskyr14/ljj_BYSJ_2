package com.cloudyearbook.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user_class")
public class UserClass {
    private Long id;
    private Long userId;
    private Long classId;
    private LocalDateTime joinTime;
    private String identity;
}
