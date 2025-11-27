package com.ayush.juryx.dto;

import com.ayush.juryx.model.CaseStatus;
import lombok.Data;

@Data
public class CaseRequest {
    private String caseTitle;
    private String description;
    private Long clientId;
    private CaseStatus status;
}
