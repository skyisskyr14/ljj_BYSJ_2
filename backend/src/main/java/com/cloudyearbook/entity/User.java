package com.cloudyearbook.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user")
public class User {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String avatarUrl;
    private Integer gender;
    private String phone;
    private String email;
    private String graduationDestination;
    private String personalProfile;
    private Integer status;
    private String role;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
