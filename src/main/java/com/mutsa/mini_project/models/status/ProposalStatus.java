package com.mutsa.mini_project.models.status;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public enum ProposalStatus {
    ACCEPT("수락"), CONFIRM("확정"), DECLINE("거절"), SUGGEST("제안");

    private final String name;

    ProposalStatus(String status) {
        this.name = status;
    }

    public static ProposalStatus valueOfName(String name) {
        return Arrays.stream(values())
                .filter(v -> v.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("옳지 않은 형식의 Status"));
    }

    public String getName() {
        return name;
    }
}
