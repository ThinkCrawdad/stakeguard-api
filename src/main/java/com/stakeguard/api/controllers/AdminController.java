package com.stakeguard.api.controllers;

import com.stakeguard.api.services.AuditService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AuditService auditService;

    public AdminController(AuditService auditService) {
        this.auditService = auditService;
    }

    @PostMapping("/selections/{selectionId}/resolve")
    public void resolveSelection(@PathVariable Long selectionId, @RequestParam String status) {
        auditService.resolveSelection(selectionId, status);
    }
}
