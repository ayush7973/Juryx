package com.ayush.juryx.service;

import com.ayush.juryx.dto.CaseRequest;
import com.ayush.juryx.dto.CaseResponse;
import com.ayush.juryx.model.CaseEntity;
import com.ayush.juryx.model.CaseStatus;
import com.ayush.juryx.model.Role;
import com.ayush.juryx.model.User;
import com.ayush.juryx.repository.CaseRepository;
import com.ayush.juryx.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CaseService {

    private final CaseRepository caseRepository;
    private final UserRepository userRepository;

    public CaseService(CaseRepository caseRepository, UserRepository userRepository) {
        this.caseRepository = caseRepository;
        this.userRepository = userRepository;
    }

    public CaseResponse createCase(CaseRequest request, Authentication auth) {
        User lawyer = userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new RuntimeException("Lawyer not found"));

        if (lawyer.getRole() != Role.LAWYER && lawyer.getRole() != Role.ADMIN) {
            throw new RuntimeException("Only lawyers or admins can create cases");
        }

        User client = userRepository.findById(request.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        CaseEntity entity = CaseEntity.builder()
                .caseTitle(request.getCaseTitle())
                .description(request.getDescription())
                .status(request.getStatus() != null ? request.getStatus() : CaseStatus.OPEN)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .lawyer(lawyer)
                .client(client)
                .build();

        return toResponse(caseRepository.save(entity));
    }

    public List<CaseResponse> getMyCases(Authentication auth) {
        User user = userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<CaseEntity> cases;
        if (user.getRole() == Role.CLIENT) {
            cases = caseRepository.findByClient(user);
        } else if (user.getRole() == Role.LAWYER) {
            cases = caseRepository.findByLawyer(user);
        } else {
            cases = caseRepository.findAll();
        }

        return cases.stream().map(this::toResponse).toList();
    }

    private CaseResponse toResponse(CaseEntity c) {
        return CaseResponse.builder()
                .id(c.getId())
                .caseTitle(c.getCaseTitle())
                .description(c.getDescription())
                .status(c.getStatus())
                .clientUsername(c.getClient() != null ? c.getClient().getUsername() : null)
                .lawyerUsername(c.getLawyer() != null ? c.getLawyer().getUsername() : null)
                .createdAt(c.getCreatedAt())
                .updatedAt(c.getUpdatedAt())
                .build();
    }
}
