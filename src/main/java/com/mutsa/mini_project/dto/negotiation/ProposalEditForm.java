package com.mutsa.mini_project.dto.negotiation;

import com.mutsa.mini_project.validator.annotation.ProposalStatusList;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProposalEditForm {
    @NotEmpty
    private String writer;
    @NotEmpty
    private String password;
    private Integer suggestedPrice;
    @ProposalStatusList
    private String status;
}
