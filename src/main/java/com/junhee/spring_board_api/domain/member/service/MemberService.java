package com.junhee.spring_board_api.domain.member.service;

import com.junhee.spring_board_api.domain.member.entity.Member;
import com.junhee.spring_board_api.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Member getMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow();
    }
}
