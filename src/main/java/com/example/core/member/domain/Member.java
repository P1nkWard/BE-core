package com.example.core.member.domain;

import com.example.core.member.domain.Address;
import com.example.core.member.domain.Phone;
import com.example.core.member.controller.dto.MemberDto;
import com.example.core.member.controller.dto.RegisterDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class Member {
    @Id
    private String id;
    @Column(nullable = false)
    private String pw;
    @Column(nullable = false)
    private String name;
    private String email;
    @Embedded
    private Phone phone;
    @Embedded
    private Address address;
    private LocalDate createDate;

    public MemberDto toMemberDto() {
        return new MemberDto(id, pw, name, email, phone, address, createDate);
    }

    public RegisterDto toRegisterDto() {
        return new RegisterDto(id, pw, name, email, phone, address);
    }
}