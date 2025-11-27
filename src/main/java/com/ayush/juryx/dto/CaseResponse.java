package com.ayush.juryx.dto;

import com.ayush.juryx.model.CaseStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CaseResponse {
    private Long id;
    private String caseTitle;
    private String description;
    private CaseStatus status;
    private String lawyerUsername;
    private String clientUsername;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
