package com.ayush.juryx.controller;

import com.ayush.juryx.dto.CaseRequest;
import com.ayush.juryx.dto.CaseResponse;
import com.ayush.juryx.service.CaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cases")
public class CaseController {

    private final CaseService caseService;

    public CaseController(CaseService caseService) {
        this.caseService = caseService;
    }

    @PostMapping
    public ResponseEntity<CaseResponse> createCase(@RequestBody CaseRequest request,
                                                   Authentication authentication) {
        return ResponseEntity.ok(caseService.createCase(request, authentication));
    }

    @GetMapping("/me")
    public ResponseEntity<List<CaseResponse>> myCases(Authentication authentication) {
        return ResponseEntity.ok(caseService.getMyCases(authentication));
    }
}
