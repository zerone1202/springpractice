package org.example.springpractice.dto;

import lombok.Getter;

@Getter
public class MemoResponse {

    private final Long id;
    private final String content;

    public MemoResponse(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
