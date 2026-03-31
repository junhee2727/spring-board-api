package com.junhee.spring_board_api.domain.member.service;

import com.junhee.spring_board_api.domain.member.dto.MemberCreateRequest;
import com.junhee.spring_board_api.domain.member.entity.Member;
import com.junhee.spring_board_api.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member getMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow();
    }

    public Member createMember(MemberCreateRequest request) {
        //비밀번호 암호화
        String encodedPwd = passwordEncoder.encode(request.getPassword());
        Member member = new Member(
                request.getEmail(),
                encodedPwd,
                request.getNickname()
        );

        return memberRepository.save(member);
    }
}
