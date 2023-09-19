package com.example.core.member.dto;

import com.example.core.member.domain.Address;
import com.example.core.member.domain.Phone;
import com.example.core.member.entity.Member;
import jakarta.validation.Valid;
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
