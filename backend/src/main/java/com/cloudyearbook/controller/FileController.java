package com.cloudyearbook.controller;

import com.cloudyearbook.common.ApiResponse;
import com.cloudyearbook.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/file")
public class FileController {
    @Value("${app.upload-dir:uploads}")
    private String uploadDir;
    private final UserService userService;

    public FileController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/avatar")
    public ApiResponse<String> uploadAvatar(MultipartFile file) throws IOException {
        String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String filename = UUID.randomUUID() + (ext == null ? "" : ("." + ext));
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();
        File target = new File(dir, filename);
        file.transferTo(target);
        String url = "/uploads/" + filename;
        userService.updateAvatar(url);
        return ApiResponse.ok(url);
    }
}
