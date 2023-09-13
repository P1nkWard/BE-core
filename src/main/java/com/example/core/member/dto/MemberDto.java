package com.example.core.member.dto;

import com.example.core.member.domain.Address;
import com.example.core.member.domain.Phone;
import com.example.core.member.entity.Member;
import jakarta.validation.Valid;
import lombok.*;

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

    public MemberDto(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    public MemberDto(String id, String pw, String name, Phone phone, Address address) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public MemberDto(Member member) {
        this.id = member.getId();
        this.pw = member.getPw();
        this.name = member.getName();
        this.email = member.getEmail();
        this.phone = member.getPhone();
        this.address = member.getAddress();
        this.createDate = member.getCreateDate();
    }
}