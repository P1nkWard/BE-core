package com.example.core.member.dto;

import com.example.core.member.domain.Address;
import com.example.core.member.domain.Phone;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class MasterDto{
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public MasterDto toEntity() {
        return new MasterDto(id,pw,name,phone,address);
    }
}
