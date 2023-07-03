package com.mutsa.mini_project.repository.negotiation;

import com.mutsa.mini_project.models.Negotiation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NegotiationRepository extends JpaRepository<Negotiation, Long> {
}
