package com.ayush.juryx.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "case_documents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CaseDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String fileType;

    private String storagePath;

    private LocalDateTime uploadedAt;

    @ManyToOne
    @JoinColumn(name = "case_id")
    private CaseEntity caseEntity;

    @ManyToOne
    @JoinColumn(name = "uploaded_by_id")
    private User uploadedBy;
}
