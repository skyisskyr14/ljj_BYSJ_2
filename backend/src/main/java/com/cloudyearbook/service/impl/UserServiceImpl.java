package com.cloudyearbook.service.impl;

import com.cloudyearbook.common.BizException;
import com.cloudyearbook.dto.ProfileUpdateDTO;
import com.cloudyearbook.entity.User;
import com.cloudyearbook.mapper.UserMapper;
import com.cloudyearbook.security.UserContext;
import com.cloudyearbook.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User currentUser() {
        User user = userMapper.selectById(UserContext.userId());
        if (user == null) {
            throw new BizException("用户不存在");
        }
        user.setPassword(null);
        return user;
    }

    @Override
    public User updateProfile(ProfileUpdateDTO dto) {
        User user = userMapper.selectById(UserContext.userId());
        if (user == null) throw new BizException("用户不存在");
        user.setNickname(dto.getNickname());
        user.setGender(dto.getGender());
        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());
        user.setGraduationDestination(dto.getGraduationDestination());
        user.setPersonalProfile(dto.getPersonalProfile());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(user);
        user.setPassword(null);
        return user;
    }

    @Override
    public String updateAvatar(String avatarUrl) {
        User user = userMapper.selectById(UserContext.userId());
        if (user == null) throw new BizException("用户不存在");
        user.setAvatarUrl(avatarUrl);
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(user);
        return avatarUrl;
    }
}
