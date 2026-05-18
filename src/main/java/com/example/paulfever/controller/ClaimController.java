package com.example.paulfever.controller;

import com.example.paulfever.entity.Claim;
import com.example.paulfever.entity.ClaimStatus;
import com.example.paulfever.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @PostMapping
    public ResponseEntity<Claim> submitClaim(@RequestBody Claim claim) {
        return ResponseEntity.ok(claimService.submitClaim(claim));
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<List<Claim>> getClaimsForItem(@PathVariable Long itemId) {
        return ResponseEntity.ok(claimService.getClaimsForItem(itemId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Claim>> getClaimsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(claimService.getClaimsByUser(userId));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Claim> updateClaimStatus(@PathVariable Long id, @RequestParam ClaimStatus status) {
        try {
            return ResponseEntity.ok(claimService.updateClaimStatus(id, status));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
