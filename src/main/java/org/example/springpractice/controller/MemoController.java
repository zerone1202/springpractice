package org.example.springpractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.springpractice.dto.MemberRequestDto;
import org.example.springpractice.dto.MemberResponseDto;
import org.example.springpractice.dto.MemoRequest;
import org.example.springpractice.dto.MemoResponse;
import org.example.springpractice.service.MemoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/memos")
    public MemoResponse createMemo(
            @RequestBody MemoRequest request
    ) {
        return memoService.save(request);
    }

    @GetMapping("/memos")
    public List<MemoResponse> getMemos() {
        return memoService.findMemos();
    }

    @GetMapping("/memos/{memoId}")
    public MemoResponse getMemo(
            @PathVariable Long memoId
    ) {
        return memoService.findMemo(memoId);
    }

    @PutMapping("/memos/{memoId}")
    public MemoResponse updateMemo(
            @PathVariable Long memoId,
            @RequestBody MemoRequest memoRequest
    ) {
        return memoService.updateMemo(memoId, memoRequest);
    }

    @DeleteMapping("/memos/{memoId}")
    public void deleteMemo(
            @PathVariable Long memoId
    ) {
        memoService.deleteMemo(memoId);
    }
}
