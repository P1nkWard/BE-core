package com.example.core.member.domain.entity;

import com.example.core.member.controller.dto.MemberDto;
import com.example.core.member.controller.dto.RegisterDto;
import com.example.core.member.domain.vo.Address;
import com.example.core.member.domain.vo.Phone;
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

    public void update(Member member) {
        this.id = member.getId();
        this.pw = member.getPw();
        this.name = member.getName();
        this.email = member.getEmail();
        this.phone = member.getPhone();
        this.address = member.getAddress();
        this.createDate = member.getCreateDate();
    }

    public MemberDto toMemberDto() {
        return new MemberDto(id, pw, name, email, phone, address, createDate);
    }

    public RegisterDto toRegisterDto() {
        return new RegisterDto(id, pw, name, email, phone, address);
    }
}