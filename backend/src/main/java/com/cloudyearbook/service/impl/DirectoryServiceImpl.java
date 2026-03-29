package com.cloudyearbook.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloudyearbook.common.PageResult;
import com.cloudyearbook.dto.DirectoryQueryDTO;
import com.cloudyearbook.entity.User;
import com.cloudyearbook.mapper.UserMapper;
import com.cloudyearbook.service.DirectoryService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class DirectoryServiceImpl implements DirectoryService {
    private final UserMapper userMapper;
    private final StringRedisTemplate redisTemplate;

    public DirectoryServiceImpl(UserMapper userMapper, StringRedisTemplate redisTemplate) {
        this.userMapper = userMapper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public PageResult<Map<String, Object>> query(DirectoryQueryDTO dto) {
        String cacheKey = "directory:list:" + dto.getKeyword() + ":" + dto.getPage() + ":" + dto.getPageSize();
        redisTemplate.opsForValue().setIfAbsent(cacheKey, "hot", 5, TimeUnit.MINUTES);
        Page<User> page = new Page<>(dto.getPage(), dto.getPageSize());
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("status", 0);
        if (dto.getKeyword() != null && !dto.getKeyword().isBlank()) {
            qw.and(w -> w.like("username", dto.getKeyword()).or().like("nickname", dto.getKeyword()));
        }
        qw.orderBy(true, "asc".equalsIgnoreCase(dto.getSortDirection()), dto.getSortBy());
        Page<User> res = userMapper.selectPage(page, qw);
        List<Map<String, Object>> records = res.getRecords().stream().map(u -> {
            Map<String, Object> m = new HashMap<>();
            m.put("id", u.getId());
            m.put("username", u.getUsername());
            m.put("nickname", u.getNickname());
            m.put("avatarUrl", u.getAvatarUrl());
            m.put("phone", u.getPhone());
            m.put("email", u.getEmail());
            m.put("graduationDestination", u.getGraduationDestination());
            return m;
        }).toList();
        return new PageResult<>(res.getTotal(), records);
    }

    @Override
    public Map<String, Object> detail(Long userId) {
        User user = userMapper.selectById(userId);
        Map<String, Object> m = new HashMap<>();
        if (user != null) {
            m.put("id", user.getId());
            m.put("username", user.getUsername());
            m.put("nickname", user.getNickname());
            m.put("avatarUrl", user.getAvatarUrl());
            m.put("phone", user.getPhone());
            m.put("email", user.getEmail());
            m.put("graduationDestination", user.getGraduationDestination());
            m.put("personalProfile", user.getPersonalProfile());
        }
        return m;
    }
}
