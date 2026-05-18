package com.example.paulfever.service;

import com.example.paulfever.entity.Claim;
import com.example.paulfever.entity.ClaimStatus;
import com.example.paulfever.repository.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaimService {

    @Autowired
    private ClaimRepository claimRepository;

    public Claim submitClaim(Claim claim) {
        return claimRepository.save(claim);
    }

    public List<Claim> getClaimsForItem(Long itemId) {
        return claimRepository.findByItemId(itemId);
    }

    public List<Claim> getClaimsByUser(Long userId) {
        return claimRepository.findByClaimantId(userId);
    }

    public Optional<Claim> getClaimById(Long id) {
        return claimRepository.findById(id);
    }

    public Claim updateClaimStatus(Long claimId, ClaimStatus status) {
        Optional<Claim> claimOpt = claimRepository.findById(claimId);
        if (claimOpt.isPresent()) {
            Claim claim = claimOpt.get();
            claim.setStatus(status);
            return claimRepository.save(claim);
        }
        throw new RuntimeException("Claim not found");
    }
}
