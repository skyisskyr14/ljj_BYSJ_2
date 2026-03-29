package com.cloudyearbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthVO {
    private String token;
    private Long userId;
    private String role;
    private String nickname;
}
