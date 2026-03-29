package com.cloudyearbook.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloudyearbook.common.PageResult;
import com.cloudyearbook.entity.Message;
import com.cloudyearbook.entity.User;
import com.cloudyearbook.mapper.ClassMapper;
import com.cloudyearbook.mapper.MessageMapper;
import com.cloudyearbook.mapper.UserMapper;
import com.cloudyearbook.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    private final UserMapper userMapper;
    private final MessageMapper messageMapper;
    private final ClassMapper classMapper;

    public AdminServiceImpl(UserMapper userMapper, MessageMapper messageMapper, ClassMapper classMapper) {
        this.userMapper = userMapper;
        this.messageMapper = messageMapper;
        this.classMapper = classMapper;
    }

    @Override
    public Map<String, Object> dashboard() {
        Map<String, Object> map = new HashMap<>();
        map.put("userCount", userMapper.selectCount(null));
        map.put("classCount", classMapper.selectCount(null));
        map.put("messageCount", messageMapper.selectCount(new QueryWrapper<Message>().eq("status", 0)));
        return map;
    }

    @Override
    public PageResult<Map<String, Object>> users(long page, long pageSize, String keyword) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        if (keyword != null && !keyword.isBlank()) {
            qw.and(w -> w.like("username", keyword).or().like("nickname", keyword));
        }
        Page<User> p = userMapper.selectPage(new Page<>(page, pageSize), qw.orderByDesc("id"));
        List<Map<String, Object>> records = p.getRecords().stream().map(u -> {
            Map<String, Object> m = new HashMap<>();
            m.put("id", u.getId());
            m.put("username", u.getUsername());
            m.put("nickname", u.getNickname());
            m.put("role", u.getRole());
            m.put("status", u.getStatus());
            return m;
        }).toList();
        return new PageResult<>(p.getTotal(), records);
    }

    @Override
    public void disableUser(Long userId, Integer status) {
        User user = userMapper.selectById(userId);
        if (user == null) return;
        user.setStatus(status);
        userMapper.updateById(user);
    }
}
