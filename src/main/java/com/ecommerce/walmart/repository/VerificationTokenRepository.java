package com.ecommerce.walmart.repository;

import com.ecommerce.walmart.Entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String theToken);
}
