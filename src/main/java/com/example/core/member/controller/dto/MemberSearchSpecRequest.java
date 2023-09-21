package com.example.core.member.controller.dto;

import com.example.core.member.domain.Address;
import com.example.core.member.domain.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberSearchSpecRequest {
    private String id;
    private String pw;
    private String name;
    private String email;
    private Phone phone;
    private Address address;
    private LocalDate createDate;
}