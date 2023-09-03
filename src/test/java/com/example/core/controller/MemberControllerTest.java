package com.example.core.controller;

import com.example.core.member.dto.MemberDto;
import com.example.core.document.config.RestDocsTestSupport;
import com.example.core.member.domain.Address;
import com.example.core.member.domain.Phone;
import com.example.core.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MemberControllerTest extends RestDocsTestSupport {

    private static ObjectMapper objectMapper = new ObjectMapper();
    @MockBean
    private MemberService memberService;

    @Test
    @DisplayName(value = "회원가입 테스트")
    public void registerMemberTest() throws Exception {
        // body 데이터
        Phone phone = new Phone("010", "0000", "0000");
        Address address = new Address("대한민국 경기도 의정부시", "경민로 00길", "401호");
        MemberDto member = new MemberDto();
        member.setId("abc");
        member.setPw("123");
        member.setName("myName");
        member.setPhone(phone);
        member.setAddress(address);
        String requestJson = objectMapper.writeValueAsString(member);

        mvc.perform(MockMvcRequestBuilders.post("/members/register")
                        .content(requestJson)
                        .header("referer", "http://localhost:8080/home")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(member.getId()))
                .andExpect(header().string("referer", "http://localhost:8080/home"));
    }

    @Test
    @DisplayName(value = "회원 조회 테스트")
    public void checkMemberTest() throws Exception {
        List<String> ids = new ArrayList<>();
        ids.add("id_1");
        ids.add("id_2");
        /*
        ids 변수는 List<String> 타입이므로 ids.toString()의 결과가 전달됨.
        따라서 경로 변수의 값은 "[id_1, id_2]"로 전달됨.
        Controller에서는 쉼표를 기준으로 나누어 List<String>타입의 변수에 저장하기 때문에 검증을 하면
        Expected :id_1
        Actual   :[id_1
        이렇게 됨. 이를 해결하기 위해
        String ids = "id_1,id_2";
        이렇게 하거나 List<String> 타입의 변수를 문자열로 변환하는 과정이 필요함.
         */

        mvc.perform(MockMvcRequestBuilders.get("/members"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "회원 수정 테스트")
    public void ModifyMemberTest() throws Exception {
        // body 데이터
        Phone phone = new Phone("010", "1111", "1111");
        Address address = new Address("대한민국 경기도 의정부시", "경민로 00길", "501호");
        MemberDto member = new MemberDto();
        member.setId("qwer");
        member.setPw("1234");
        member.setName("newName");
        member.setPhone(phone);
        member.setAddress(address);
        String requestJson = objectMapper.writeValueAsString(member);

        mvc.perform(MockMvcRequestBuilders.put("/members")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("회원 정보 수정 성공"));
    }

    @Test
    @DisplayName(value = "회원 삭제 테스트")
    public void deleteMemberTest() throws Exception {
        List<String> ids = new ArrayList<>();
        ids.add("id_1");
        ids.add("id_2");
        ids.add("id_3");
        String idString = String.join(",", ids);

        mvc.perform(MockMvcRequestBuilders.delete("/members/{ids}", idString))
                .andExpect(status().isOk())
                .andExpect(content().string("회원 삭제 성공"));
    }

    @Test
    @DisplayName(value = "회원 id 찾기 테스트")
    public void findMemberIdTest() throws Exception {
        Phone phone = new Phone("010", "0000", "0000");
        Address address = new Address("대한민국 경기도 의정부시", "경민로 00길", "401호");
        // body 데이터
        MemberDto member = new MemberDto();
        member.setName("myName");
        member.setPhone(phone);
        String requestJson = objectMapper.writeValueAsString(member);

        mvc.perform(MockMvcRequestBuilders.post("/members/find-id")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("찾은 아이디"));
    }

    @Test
    @DisplayName(value = "회원 pw 찾기 테스트")
    public void findMemberPwTest() throws Exception {
        Phone phone = new Phone("010", "0000", "0000");
        Address address = new Address("대한민국 경기도 의정부시", "경민로 00길", "401호");
        // body 데이터
        MemberDto member = new MemberDto();
        member.setId("abc");
        member.setPhone(phone);
        String requestJson = objectMapper.writeValueAsString(member);

        mvc.perform(MockMvcRequestBuilders.post("/members/find-pw")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("찾은 비밀번호"));
    }

    @Test
    @DisplayName(value = "로그인 성공 테스트")
    public void loginSuccessTest() throws Exception {
        String id = "abc";
        String pw = "123";
        String referer = "http://localhost:8080/home";

        when(memberService.login(any(MemberDto.class))).thenReturn(true);

        mvc.perform(MockMvcRequestBuilders.post("/members/login")
                        .param("id", id)
                        .param("pw", pw)
                        .header("referer", referer)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string("referer", referer))
                .andExpect(content().string(id));
    }

    @Test
    @DisplayName(value = "로그인 실패 테스트")
    public void loginFailTest() throws Exception {
        String id = "abc";
        String pw = "123";
        String referer = "http://localhost:8080/home";

        when(memberService.login(any(MemberDto.class))).thenReturn(false);

        mvc.perform(MockMvcRequestBuilders.post("/members/login")
                        .param("id", id)
                        .param("pw", pw)
                        .header("referer", referer)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(header().string("referer", referer))
                .andExpect(content().string("로그인 실패"));
    }
}