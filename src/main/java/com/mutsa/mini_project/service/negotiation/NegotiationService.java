package com.mutsa.mini_project.service.negotiation;

import com.mutsa.mini_project.repository.negotiation.NegotiationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NegotiationService {
    private final NegotiationRepository negotiationRepository;
}
