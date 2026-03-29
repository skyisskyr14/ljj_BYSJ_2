package com.cloudyearbook.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloudyearbook.common.BizException;
import com.cloudyearbook.common.PageResult;
import com.cloudyearbook.dto.MessageCreateDTO;
import com.cloudyearbook.entity.Message;
import com.cloudyearbook.entity.User;
import com.cloudyearbook.mapper.MessageMapper;
import com.cloudyearbook.mapper.UserMapper;
import com.cloudyearbook.security.UserContext;
import com.cloudyearbook.service.MessageService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageMapper messageMapper;
    private final UserMapper userMapper;

    public MessageServiceImpl(MessageMapper messageMapper, UserMapper userMapper) {
        this.messageMapper = messageMapper;
        this.userMapper = userMapper;
    }

    @Override
    public void create(MessageCreateDTO dto) {
        Message message = new Message();
        message.setContent(dto.getContent());
        message.setSenderId(UserContext.userId());
        message.setParentId(dto.getParentId());
        message.setStatus(0);
        message.setLikeCount(0);
        message.setCreateTime(LocalDateTime.now());
        message.setUpdateTime(LocalDateTime.now());
        messageMapper.insert(message);
    }

    @Override
    public PageResult<Map<String, Object>> page(long page, long pageSize) {
        Page<Message> p = messageMapper.selectPage(new Page<>(page, pageSize), new QueryWrapper<Message>().eq("status", 0).orderByDesc("id"));
        List<Map<String, Object>> list = p.getRecords().stream().map(msg -> {
            User sender = userMapper.selectById(msg.getSenderId());
            Map<String, Object> m = new HashMap<>();
            m.put("id", msg.getId());
            m.put("content", msg.getContent());
            m.put("createTime", msg.getCreateTime());
            m.put("senderName", sender == null ? "未知用户" : sender.getNickname());
            m.put("senderAvatar", sender == null ? null : sender.getAvatarUrl());
            return m;
        }).toList();
        return new PageResult<>(p.getTotal(), list);
    }

    @Override
    public void remove(Long id) {
        Message msg = messageMapper.selectById(id);
        if (msg == null) throw new BizException("留言不存在");
        if (!"ADMIN".equals(UserContext.role()) && !UserContext.userId().equals(msg.getSenderId())) {
            throw new BizException("无权限删除");
        }
        msg.setStatus(1);
        msg.setUpdateTime(LocalDateTime.now());
        messageMapper.updateById(msg);
    }
}
