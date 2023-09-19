package com.example.core.member.dto;

import com.example.core.member.domain.Address;
import com.example.core.member.domain.Phone;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private String id;
    private String pw;
    private String name;
    private String email;
    @Valid
    private Phone phone;
    @Valid
    private Address address;
    private LocalDate createDate;
}