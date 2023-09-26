package com.example.core.member.domain.vo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MemberSearchSpec {
    private String id;
    private String pw;
    private String name;
    private String email;
    private Phone phone;
    private Address address;
    private LocalDate createDate;
}
