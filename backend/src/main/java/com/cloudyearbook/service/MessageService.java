package com.cloudyearbook.service;

import com.cloudyearbook.common.PageResult;
import com.cloudyearbook.dto.MessageCreateDTO;

import java.util.Map;

public interface MessageService {
    void create(MessageCreateDTO dto);
    PageResult<Map<String, Object>> page(long page, long pageSize);
    void remove(Long id);
}
