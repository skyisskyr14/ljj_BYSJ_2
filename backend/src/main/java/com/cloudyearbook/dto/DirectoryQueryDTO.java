package com.cloudyearbook.dto;

import lombok.Data;

@Data
public class DirectoryQueryDTO {
    private String keyword;
    private Long classId;
    private Long tagId;
    private String sortBy = "id";
    private String sortDirection = "desc";
    private long page = 1;
    private long pageSize = 10;
}
