package com.cloudyearbook.service;

import com.cloudyearbook.common.PageResult;
import com.cloudyearbook.dto.DirectoryQueryDTO;

import java.util.Map;

public interface DirectoryService {
    PageResult<Map<String, Object>> query(DirectoryQueryDTO dto);
    Map<String, Object> detail(Long userId);
}
