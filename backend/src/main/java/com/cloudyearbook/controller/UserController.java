package com.cloudyearbook.controller;

import com.cloudyearbook.common.ApiResponse;
import com.cloudyearbook.dto.ProfileUpdateDTO;
import com.cloudyearbook.entity.User;
import com.cloudyearbook.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ApiResponse<User> me() {
        return ApiResponse.ok(userService.currentUser());
    }

    @PutMapping("/me")
    public ApiResponse<User> update(@RequestBody ProfileUpdateDTO dto) {
        return ApiResponse.ok("更新成功", userService.updateProfile(dto));
    }
}
