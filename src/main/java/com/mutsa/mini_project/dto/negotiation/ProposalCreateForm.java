package com.mutsa.mini_project.dto.negotiation;

import com.mutsa.mini_project.models.Negotiation;
import com.mutsa.mini_project.models.embedded.RequiredWriter;
import com.mutsa.mini_project.models.status.ProposalStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProposalCreateForm {
    @NotEmpty
    private String writer;
    @NotEmpty
    private String password;
    @Min(0)
    @NotNull
    private Integer suggestedPrice;

    public static Negotiation toEntity(ProposalCreateForm form) {
        return Negotiation.builder()
                .suggestedPrice(form.getSuggestedPrice())
                .requiredWriter(RequiredWriter.of(form.getWriter(),
                        form.getPassword()))
                .status(ProposalStatus.SUGGEST)
                .build();
    }
}
