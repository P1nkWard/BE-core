package com.example.core.controller;

import com.example.core.member.controller.MemberController;
import com.example.core.member.dto.LoginDto;
import com.example.core.member.dto.MemberDto;
import com.example.core.document.config.RestDocsTestSupport;
import com.example.core.member.domain.Address;
import com.example.core.member.domain.Phone;
import com.example.core.member.dto.MemberSearchSpecRequest;
import com.example.core.member.exception.InvalidCredentialsException;
import com.example.core.member.service.LoginService;
import com.example.core.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
public class MemberControllerTest extends RestDocsTestSupport {

    private static ObjectMapper objectMapper = new ObjectMapper();
    @MockBean
    private MemberService memberService;
    @MockBean
    private LoginService loginService;

    private Phone phone;
    private Address address;
    private MemberDto member;
    private LoginDto  loginDto;
    private String referer;
    private String exceptionMsg;

    @BeforeEach
    void setUp() {
        phone = new Phone("010", "0000", "0000");
        address = new Address("대한민국 경기도 의정부시", "경민로 00길", "401호");
        member = new MemberDto("abc", "123", "myName", phone, address);
        loginDto = new LoginDto("abc", "123");
        referer = "http://localhost:8080/home";
        exceptionMsg = "아이디 또는 비밀번호가 잘못되었습니다";
    }

    @Test
    @DisplayName(value = "회원가입 테스트")
    public void registerMemberTest() throws Exception {
        // body 데이터
        Phone phone = new Phone("010", "0000", "0000");
        Address address = new Address("대한민국 경기도 의정부시1", "경민로 00길", "401호");
        MemberDto member = new MemberDto();
        member.setId("abc");
        member.setPw("123");
        member.setName("myName");
        member.setPhone(phone);
        member.setAddress(address);
        when(memberService.register(any())).thenReturn(true);
        String requestJson = objectMapper.writeValueAsString(member);
        System.out.println(requestJson+"리퀘스트제이슨");
        mvc.perform(MockMvcRequestBuilders.post("/members/register")
                        .content(requestJson)
                        .header("referer", "http://localhost:8080/home")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("회원가입 성공"))
                .andExpect(jsonPath("$.id").value("abc"))
                .andExpect(header().string("referer", "http://localhost:8080/home"));
    }

    @Test
    @DisplayName(value = "로그인 성공 테스트")
    public void loginSuccessTest() throws Exception {
        doNothing().when(loginService).login(any(LoginDto.class));
        String requestJson = objectMapper.writeValueAsString(loginDto);

        mvc.perform(MockMvcRequestBuilders.post("/members/login")
                        .content(requestJson)
                        .header("referer", referer)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string("referer", referer))
                .andExpect(jsonPath("$.id").value(loginDto.getId()))
                .andExpect(jsonPath("$.message").value("로그인 성공"));
    }

    @Test
    @DisplayName(value = "로그인 실패 테스트 - 존재하지 않는 아이디")
    public void loginFailTestWithNonexistentId() throws Exception {
        doThrow(new InvalidCredentialsException(exceptionMsg)).when(loginService).login(any(LoginDto.class));
        String requestJson = objectMapper.writeValueAsString(loginDto);

        mvc.perform(MockMvcRequestBuilders.post("/members/login")
                        .content(requestJson)
                        .header("referer", referer)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value(exceptionMsg));
    }

    @Test
    @DisplayName(value = "로그인 실패 테스트 - 비밀번호 불일치")
    public void loginFailTestWithWrongPassword() throws Exception {
        doThrow(new InvalidCredentialsException(exceptionMsg)).when(loginService).login(any(LoginDto.class));
        String requestJson = objectMapper.writeValueAsString(loginDto);

        mvc.perform(MockMvcRequestBuilders.post("/members/login")
                        .content(requestJson)
                        .header("referer", referer)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value(exceptionMsg));
    }

    @Test
    @DisplayName(value = "회원 조회 테스트")
    public void findListTest() throws Exception {
        List<MemberDto> members = List.of(new MemberDto("abc", "123"));
        List<String> ids = List.of("abc", "qwer");
        MemberSearchSpecRequest searchSpec = new MemberSearchSpecRequest();

        searchSpec.setIds(ids);
        String requestJson = objectMapper.writeValueAsString(searchSpec);

        when(memberService.search(searchSpec)).thenReturn(members);

        mvc.perform(MockMvcRequestBuilders.post("/members/inquiry")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(members.get(0).getId()));
    }

    @Test
    @DisplayName(value = "회원 수정 테스트")
    public void ModifyMemberTest() throws Exception {
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
        String requestJson = objectMapper.writeValueAsString(member);

        mvc.perform(MockMvcRequestBuilders.post("/members/find-pw")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("찾은 비밀번호"));
    }
}