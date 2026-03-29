package com.cloudyearbook.controller;

import com.cloudyearbook.common.ApiResponse;
import com.cloudyearbook.dto.LoginDTO;
import com.cloudyearbook.dto.RegisterDTO;
import com.cloudyearbook.service.AuthService;
import com.cloudyearbook.vo.AuthVO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ApiResponse<AuthVO> login(@Valid @RequestBody LoginDTO dto) {
        return ApiResponse.ok(authService.login(dto));
    }

    @PostMapping("/register")
    public ApiResponse<AuthVO> register(@Valid @RequestBody RegisterDTO dto) {
        return ApiResponse.ok(authService.register(dto));
    }
}
