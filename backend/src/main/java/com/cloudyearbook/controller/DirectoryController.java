package com.cloudyearbook.controller;

import com.cloudyearbook.common.ApiResponse;
import com.cloudyearbook.common.PageResult;
import com.cloudyearbook.dto.DirectoryQueryDTO;
import com.cloudyearbook.service.DirectoryService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/directory")
public class DirectoryController {
    private final DirectoryService directoryService;

    public DirectoryController(DirectoryService directoryService) {
        this.directoryService = directoryService;
    }

    @PostMapping("/search")
    public ApiResponse<PageResult<Map<String, Object>>> search(@RequestBody DirectoryQueryDTO dto) {
        return ApiResponse.ok(directoryService.query(dto));
    }

    @GetMapping("/{userId}")
    public ApiResponse<Map<String, Object>> detail(@PathVariable Long userId) {
        return ApiResponse.ok(directoryService.detail(userId));
    }
}
