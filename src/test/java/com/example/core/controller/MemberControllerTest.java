package com.example.core.controller;

import com.example.core.document.config.RestDocsTestSupport;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.swing.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MemberControllerTest extends RestDocsTestSupport {
    static class MemberInfo {
        String id;
        String pw;
        String name;
        String phone;
        String address;

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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName(value = "회원 id 찾기 테스트")
    public void findMemberIdTest() throws Exception {
        // body 데이터
        MemberInfo member = new MemberInfo();
        member.setName("myName");
        member.setPhone("010-0000-0000");
        String requestJson = objectMapper.writeValueAsString(member);

        mvc.perform(MockMvcRequestBuilders.post("/find-id")
                        .content(requestJson))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName(value = "회원 pw 찾기 테스트")
    public void findMemberPwTest() throws Exception {
        // body 데이터
        MemberInfo member = new MemberInfo();
        member.setId("abc");
        member.setPhone("010-0000-0000");
        String requestJson = objectMapper.writeValueAsString(member);

        mvc.perform(MockMvcRequestBuilders.post("/find-pw")
                        .content(requestJson))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName(value = "회원 생성 테스트")
    public void registerMemberTest() throws Exception {
        // body 데이터
        MemberInfo member = new MemberInfo();
        member.setId("abc");
        member.setPw("123");
        member.setName("myName");
        member.setPhone("010-0000-0000");
        member.setAddress("myAddress");
        String requestJson = objectMapper.writeValueAsString(member);

        mvc.perform(MockMvcRequestBuilders.post("/register")
                        .content(requestJson))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName(value = "회원 수정 테스트")
    public void ModifyMemberTest() throws Exception {
        // body 데이터
        MemberInfo member = new MemberInfo();
        member.setId("qwer");
        member.setPw("1234");
        member.setName("newName");
        member.setPhone("010-1111-1111");
        member.setAddress("newAddress");
        String requestJson = objectMapper.writeValueAsString(member);

        mvc.perform(MockMvcRequestBuilders.post("/modify")
                        .content(requestJson))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName(value = "회원 삭제 테스트")
    public void deleteMemberTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/delete/{id}", "id"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName(value = "회원 조회 테스트")
    public void checkMemberTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/check/{id}", "id"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName(value = "결제 내역 조회 테스트")
    public void checkPaymentDetailsTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/payment/{id}", "id"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}