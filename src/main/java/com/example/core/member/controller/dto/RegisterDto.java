package com.example.core.member.controller.dto;

import com.example.core.member.domain.Address;
import com.example.core.member.domain.Phone;
import com.example.core.member.domain.Member;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    @NotBlank
    private String id;

    @NotBlank
    private String pw;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @Valid
    private Phone phone;

    @Valid
    private Address address;

    public Member toEntity() {
        return new Member(id, pw, name, email, phone, address, null);
    }
}