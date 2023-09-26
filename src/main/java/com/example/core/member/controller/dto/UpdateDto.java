package com.example.core.member.controller.dto;

import com.example.core.member.domain.vo.Address;
import com.example.core.member.domain.vo.Phone;
import com.example.core.member.domain.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDto {
    @NotBlank
    private String id;
    private String pw;
    private String name;
    @Email
    private String email;
    private Phone phone;
    private Address address;

    public Member toEntity() {
        return new Member(id, pw, name, email, phone, address, null);
    }
}
