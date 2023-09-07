package com.example.core.member.dto;

import com.example.core.member.domain.Address;
import com.example.core.member.domain.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberSearchSpecRequest {
    private List<String> ids;
    private List<String> pws;
    private List<String> names;
    private List<String> emails;
    private List<Phone> phones;
    private List<Address> addresses;
    private List<LocalDate> createDates;
}