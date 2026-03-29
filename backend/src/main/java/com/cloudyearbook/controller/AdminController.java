package com.cloudyearbook.controller;

import com.cloudyearbook.common.ApiResponse;
import com.cloudyearbook.common.PageResult;
import com.cloudyearbook.service.AdminService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/dashboard")
    public ApiResponse<Map<String, Object>> dashboard() { return ApiResponse.ok(adminService.dashboard()); }

    @GetMapping("/users")
    public ApiResponse<PageResult<Map<String, Object>>> users(@RequestParam(defaultValue = "1") long page,
                                                              @RequestParam(defaultValue = "10") long pageSize,
                                                              @RequestParam(defaultValue = "") String keyword) {
        return ApiResponse.ok(adminService.users(page, pageSize, keyword));
    }

    @PutMapping("/users/{userId}/status")
    public ApiResponse<Void> status(@PathVariable Long userId, @RequestParam Integer status) {
        adminService.disableUser(userId, status);
        return ApiResponse.ok(null);
    }
}
