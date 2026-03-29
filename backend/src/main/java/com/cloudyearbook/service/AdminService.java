package com.cloudyearbook.service;

import com.cloudyearbook.common.PageResult;

import java.util.Map;

public interface AdminService {
    Map<String, Object> dashboard();
    PageResult<Map<String, Object>> users(long page, long pageSize, String keyword);
    void disableUser(Long userId, Integer status);
}
