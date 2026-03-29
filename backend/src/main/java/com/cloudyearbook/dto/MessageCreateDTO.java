package com.cloudyearbook.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MessageCreateDTO {
    @NotBlank
    private String content;
    private Long parentId = 0L;
}
