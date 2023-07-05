package com.mutsa.mini_project.repository.negotiation;

import com.mutsa.mini_project.models.Negotiation;
import com.mutsa.mini_project.models.SalesItem;
import com.mutsa.mini_project.models.embedded.RequiredWriter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NegotiationRepository extends JpaRepository<Negotiation, Long> {
    Page<Negotiation> findNegotiationsBySalesItemEqualsAndRequiredWriter(SalesItem item, RequiredWriter of, Pageable pageable);

    Page<Negotiation> findNegotiationsBySalesItemEquals(SalesItem item, Pageable pageable);

    Optional<Negotiation> findByIdAndSalesItemAndRequiredWriterEquals(Long proposalId, SalesItem item, RequiredWriter of);

    Optional<Negotiation> findBySalesItemEqualsAndId(SalesItem item, Long proposalId);

    List<Negotiation> findNegotiationsBySalesItem(SalesItem item);

}
