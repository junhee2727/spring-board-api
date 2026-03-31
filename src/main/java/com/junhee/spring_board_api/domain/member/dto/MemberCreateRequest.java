package com.junhee.spring_board_api.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberCreateRequest {
    @NotBlank(message ="이메일은 필수입니다.")
    @jakarta.validation.constraints.Email(message = "이메일 형식이 올바르지 않습니다.")
    @Size(max = 100)
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, max = 255, message = "비밀번호는 8자 이상입니다.")
    private String password;

    @NotBlank(message = "닉네임은 필수입니다.")
    @Size(max = 50)
    private String nickname;
}
