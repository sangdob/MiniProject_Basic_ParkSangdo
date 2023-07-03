package com.mutsa.mini_project.contorller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Response {
    private String message;
    private HttpStatus status;

    public static Response of(final SuccessCode code) {
        return new Response(code.getMessage(), code.getStatus());
    }
}
