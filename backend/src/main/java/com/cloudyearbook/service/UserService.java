package com.cloudyearbook.service;

import com.cloudyearbook.dto.ProfileUpdateDTO;
import com.cloudyearbook.entity.User;

public interface UserService {
    User currentUser();
    User updateProfile(ProfileUpdateDTO dto);
    String updateAvatar(String avatarUrl);
}
