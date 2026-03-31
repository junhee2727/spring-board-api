package com.junhee.spring_board_api.domain.member.entity;

import com.junhee.spring_board_api.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
//Table member {
//member_id   bigint      [pk, increment,  note: '회원 PK']
//email       varchar(100) [not null, unique, note: '이메일 (로그인 ID)']
//password    varchar(255) [not null,         note: '비밀번호 (BCrypt 해시)']
//nickname    varchar(50)  [not null, unique, note: '닉네임']
//role        varchar(10)  [not null, default: 'USER', note: 'USER | ADMIN']
//created_at  datetime     [not null, default: `CURRENT_TIMESTAMP`, note: '가입일']
//updated_at  datetime     [not null, default: `CURRENT_TIMESTAMP`, note: '수정일']
//        }


@NoArgsConstructor
@Getter
@Entity
@Table(name = "member")
public class Member extends BaseEntity {
    public enum Role {
        USER, ADMIN,
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    @Column(length = 100, nullable = false, unique = true)
    private String email;
    @Column(length = 255, nullable = false)
    private String password;
    @Column(length = 50, nullable = false, unique = true)
    private String nickname;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.USER;

    public Member(String email, String password, String nickname){
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
}
