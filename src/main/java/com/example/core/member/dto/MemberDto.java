package com.example.core.member.dto;

import com.example.core.member.domain.Address;
import com.example.core.member.domain.Phone;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    @NotBlank
    private String id;
    @NotBlank
    private String pw;
    @NotBlank
    private String name;
    @Valid
    private Phone phone;
    @Valid
    private Address address;

    public MemberDto(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }
}
