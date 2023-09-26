package com.example.core.member.controller.dto;

import com.example.core.member.domain.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotBlank
    private String id;

    @NotBlank
    private String pw;

    public Member toEntity() {
        return new Member(id, pw, null, null, null, null, null);
    }
}