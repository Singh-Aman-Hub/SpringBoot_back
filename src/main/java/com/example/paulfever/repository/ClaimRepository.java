package com.example.paulfever.repository;

import com.example.paulfever.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    List<Claim> findByItemId(Long itemId);
    List<Claim> findByClaimantId(Long claimantId);
}
