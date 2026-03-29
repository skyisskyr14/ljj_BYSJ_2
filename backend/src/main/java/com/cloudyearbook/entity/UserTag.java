package com.cloudyearbook.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user_tag")
public class UserTag {
    private Long id;
    private Long userId;
    private Long tagId;
    private LocalDateTime createTime;
}
