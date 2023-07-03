package com.mutsa.mini_project.models.embedded;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RequiredWriter {
    private String writer;
    private String password;

    public static RequiredWriter of(String writer, String password) {
        return RequiredWriter.builder()
                .writer(writer)
                .password(password)
                .build();
    }
}
