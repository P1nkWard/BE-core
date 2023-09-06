package com.example.core.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {
    @NotBlank
    String masterId;
    // 연관관계를 맺어줘야함 일단 이름만 해놓음
    @NotBlank
    String storeName;

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    public StoreDto toEntity()  {
        return new StoreDto(masterId,storeName);
    }
}
