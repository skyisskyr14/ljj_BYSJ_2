package com.cloudyearbook.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("operation_log")
public class OperationLog {
    private Long id;
    private Long userId;
    private String operationType;
    private String operationDetail;
    private String ipAddress;
    private String userAgent;
    private Integer status;
    private LocalDateTime createTime;
}
