package com.ayush.juryx.repository;

import com.ayush.juryx.model.CaseEntity;
import com.ayush.juryx.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaseRepository extends JpaRepository<CaseEntity, Long> {
    List<CaseEntity> findByClient(User client);
    List<CaseEntity> findByLawyer(User lawyer);
}
