package com.cloudyearbook.controller;

import com.cloudyearbook.common.ApiResponse;
import com.cloudyearbook.common.PageResult;
import com.cloudyearbook.dto.MessageCreateDTO;
import com.cloudyearbook.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ApiResponse<Void> create(@Valid @RequestBody MessageCreateDTO dto) {
        messageService.create(dto);
        return ApiResponse.ok(null);
    }

    @GetMapping
    public ApiResponse<PageResult<Map<String, Object>>> page(@RequestParam(defaultValue = "1") long page,
                                                              @RequestParam(defaultValue = "10") long pageSize) {
        return ApiResponse.ok(messageService.page(page, pageSize));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        messageService.remove(id);
        return ApiResponse.ok(null);
    }
}
