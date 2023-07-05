package com.mutsa.mini_project.dto.negotiation;

import com.mutsa.mini_project.models.Negotiation;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProposalRes {
    private Long id;
    private int suggestedPrice;
    private String status;

    public static ProposalRes of(Negotiation negotiation) {
        return new ProposalRes(negotiation.getId(),
                negotiation.getSuggestedPrice(),
                negotiation.getStatus().getName());
    }
}
