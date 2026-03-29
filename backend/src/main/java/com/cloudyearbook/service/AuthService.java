package com.cloudyearbook.service;

import com.cloudyearbook.dto.LoginDTO;
import com.cloudyearbook.dto.RegisterDTO;
import com.cloudyearbook.vo.AuthVO;

public interface AuthService {
    AuthVO login(LoginDTO dto);
    AuthVO register(RegisterDTO dto);
}
