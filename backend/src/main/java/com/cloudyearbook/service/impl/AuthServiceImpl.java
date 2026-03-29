package com.cloudyearbook.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cloudyearbook.common.BizException;
import com.cloudyearbook.dto.LoginDTO;
import com.cloudyearbook.dto.RegisterDTO;
import com.cloudyearbook.entity.OperationLog;
import com.cloudyearbook.entity.User;
import com.cloudyearbook.mapper.OperationLogMapper;
import com.cloudyearbook.mapper.UserMapper;
import com.cloudyearbook.security.JwtUtil;
import com.cloudyearbook.service.AuthService;
import com.cloudyearbook.vo.AuthVO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final OperationLogMapper operationLogMapper;

    public AuthServiceImpl(UserMapper userMapper, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, OperationLogMapper operationLogMapper) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.operationLogMapper = operationLogMapper;
    }

    @Override
    public AuthVO login(LoginDTO dto) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, dto.getUsername()));
        if (user == null || !passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new BizException("用户名或密码错误");
        }
        if (user.getStatus() != 0) {
            throw new BizException("账号已被禁用");
        }
        saveLog(user.getId(), "LOGIN", "用户登录");
        String token = jwtUtil.generateToken(user.getId(), user.getRole());
        return new AuthVO(token, user.getId(), user.getRole(), user.getNickname());
    }

    @Override
    public AuthVO register(RegisterDTO dto) {
        long count = userMapper.selectCount(new LambdaQueryWrapper<User>().eq(User::getUsername, dto.getUsername()));
        if (count > 0) {
            throw new BizException("用户名已存在");
        }
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setNickname(dto.getNickname());
        user.setRole("USER");
        user.setStatus(0);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
        saveLog(user.getId(), "REGISTER", "用户注册");
        return new AuthVO(jwtUtil.generateToken(user.getId(), user.getRole()), user.getId(), user.getRole(), user.getNickname());
    }

    private void saveLog(Long userId, String type, String detail) {
        OperationLog log = new OperationLog();
        log.setUserId(userId);
        log.setOperationType(type);
        log.setOperationDetail(detail);
        log.setStatus(0);
        log.setCreateTime(LocalDateTime.now());
        operationLogMapper.insert(log);
    }
}
