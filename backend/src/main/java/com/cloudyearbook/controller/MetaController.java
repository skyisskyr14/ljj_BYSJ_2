package com.cloudyearbook.controller;

import com.cloudyearbook.common.ApiResponse;
import com.cloudyearbook.dto.ClassDTO;
import com.cloudyearbook.dto.TagDTO;
import com.cloudyearbook.service.MetaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meta")
public class MetaController {
    private final MetaService metaService;

    public MetaController(MetaService metaService) {
        this.metaService = metaService;
    }

    @GetMapping("/classes")
    public ApiResponse<List<ClassDTO>> classes() { return ApiResponse.ok(metaService.classes()); }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/classes")
    public ApiResponse<Void> saveClass(@RequestBody ClassDTO dto) { metaService.saveClass(dto); return ApiResponse.ok(null); }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/classes/{id}")
    public ApiResponse<Void> delClass(@PathVariable Long id) { metaService.deleteClass(id); return ApiResponse.ok(null); }

    @GetMapping("/tags")
    public ApiResponse<List<TagDTO>> tags() { return ApiResponse.ok(metaService.tags()); }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/tags")
    public ApiResponse<Void> saveTag(@RequestBody TagDTO dto) { metaService.saveTag(dto); return ApiResponse.ok(null); }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/tags/{id}")
    public ApiResponse<Void> delTag(@PathVariable Long id) { metaService.deleteTag(id); return ApiResponse.ok(null); }
}
