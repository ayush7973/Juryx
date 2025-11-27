package com.ayush.juryx.repository;

import com.ayush.juryx.model.CaseDocument;
import com.ayush.juryx.model.CaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaseDocumentRepository extends JpaRepository<CaseDocument, Long> {
    List<CaseDocument> findByCaseEntity(CaseEntity caseEntity);
}
