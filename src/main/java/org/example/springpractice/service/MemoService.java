package org.example.springpractice.service;

import lombok.RequiredArgsConstructor;
import org.example.springpractice.dto.MemberRequestDto;
import org.example.springpractice.dto.MemberResponseDto;
import org.example.springpractice.dto.MemoRequest;
import org.example.springpractice.dto.MemoResponse;
import org.example.springpractice.entity.Member;
import org.example.springpractice.entity.Memo;
import org.example.springpractice.repository.MemoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public MemoResponse save(MemoRequest request) {
        Memo savedMemo = memoRepository.save(new Memo(request.getContent()));
        return new MemoResponse(savedMemo.getId(), savedMemo.getContent());
    }

    @Transactional(readOnly = true)
    public List<MemoResponse> findMemos() {
        List<Memo> memos = memoRepository.findAll();

        List<MemoResponse> dtos = new ArrayList<>();

        for (Memo memo : memos) {
            dtos.add(new MemoResponse(memo.getId(), memo.getContent()));
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public MemoResponse findMemo(Long memoId) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new IllegalArgumentException("그런 메모는 없습니다.")
        );
        return new MemoResponse(memo.getId(), memo.getContent());
    }

    @Transactional
    public MemoResponse updateMemo(Long memoId, MemoRequest request) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new IllegalArgumentException("그런 메모는 없습니다.")
        );
        memo.updateContent(request.getContent());

        return new MemoResponse(memo.getId(), memo.getContent());
    }

    @Transactional
    public void deleteMemo(Long id) {
        memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("그런 메모는 없습니다.")
        );
        memoRepository.deleteById(id);
    }
}
