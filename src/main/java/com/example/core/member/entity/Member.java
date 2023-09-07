package com.example.core.member.entity;

import com.example.core.member.domain.Address;
import com.example.core.member.domain.Phone;
import com.example.core.member.dto.MemberDto;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Member(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    public Member(MemberDto dto) {
        this.id = dto.getId();
        this.pw = dto.getPw();
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.phone = dto.getPhone();
        this.address = dto.getAddress();
        this.createDate = dto.getCreateDate();
    }
}