package com.mutsa.mini_project.entity.embedded;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RequiredWriter {

    private String status;

    private String writer;

    private String password;
}
