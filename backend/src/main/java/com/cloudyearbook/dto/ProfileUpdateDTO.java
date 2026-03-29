package com.cloudyearbook.dto;

import lombok.Data;

@Data
public class ProfileUpdateDTO {
    private String nickname;
    private Integer gender;
    private String phone;
    private String email;
    private String graduationDestination;
    private String personalProfile;
}
