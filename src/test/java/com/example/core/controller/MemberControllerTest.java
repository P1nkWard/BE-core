package com.example.core.controller;

import com.example.core.document.config.RestDocsConfig;
import com.example.core.document.config.RestDocsTestSupport;
import com.example.core.member.controller.MemberController;
import com.example.core.member.controller.dto.MemberDto;
import com.example.core.member.controller.dto.MemberSearchSpecRequest;
import com.example.core.member.controller.dto.RegisterDto;
import com.example.core.member.domain.vo.Address;
import com.example.core.member.domain.vo.Phone;
import com.example.core.member.exception.MemberAlreadyExistsException;
import com.example.core.member.exception.NotFoundMemberException;
import com.example.core.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
@AutoConfigureRestDocs
@Import(RestDocsConfig.class)
public class MemberControllerTest extends RestDocsTestSupport {
    @MockBean
    private MemberService memberService;

    private String id, pw, name, email, referer;
    private Phone phone;
    private Address address;

    @BeforeEach
    void setUp() {
        id = "abc";
        pw = "123";
        name = "myName";
        email = "test@test.com";
        phone = new Phone("010", "0000", "0000");
        address = new Address("대한민국 경기도 의정부시", "경민로 00길", "401호");
        referer = "http://localhost:8080/home";
    }

    @Test
    @DisplayName(value = "회원가입 성공 테스트")
    public void registerSuccessTest() throws Exception {
        doNothing().when(memberService).register(any(RegisterDto.class));

        RegisterDto dto = new RegisterDto(id, pw, name, email, phone, address);
        String requestJson = objectMapper.writeValueAsString(dto);

        mvc.perform(MockMvcRequestBuilders.post("/members/register")
                        .content(requestJson)
                        .header("referer", referer)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("referer", referer))
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    @DisplayName(value = "회원가입 실패 테스트 - 이미 존재하는 아이디")
    public void registerFailTestWithExistingId() throws Exception {
        doThrow(new MemberAlreadyExistsException("이미 존재하는 아이디입니다")).when(memberService).register(any(RegisterDto.class));

        RegisterDto dto = new RegisterDto(id, pw, name, email, phone, address);
        String requestJson = objectMapper.writeValueAsString(dto);

        mvc.perform(MockMvcRequestBuilders.post("/members/register")
                        .content(requestJson)
                        .header("referer", referer)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName(value = "회원가입 실패 테스트 - 유효하지 않은 입력값")
    public void registerFailTestWithInvalidInput() throws Exception {
        phone = new Phone();
        RegisterDto dto = new RegisterDto(id, "", name, email, phone, address);

        String requestJson = objectMapper.writeValueAsString(dto);
        mvc.perform(MockMvcRequestBuilders.post("/members/register")
                        .content(requestJson)
                        .header("referer", referer)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName(value = "회원 조회 성공 테스트")
    public void findListSuccessTest() throws Exception {
        List<MemberDto> members = List.of(new MemberDto(id, pw, name, email, phone, address, null));
        MemberSearchSpecRequest searchSpec = new MemberSearchSpecRequest();
        searchSpec.setName("myName");

        when(memberService.findList(searchSpec)).thenReturn(members);

        mvc.perform(MockMvcRequestBuilders.get("/members")
                        // 컨트롤러는 get 요청에서 @ModelAttribute로 쿼리 매개변수를 바인딩 해줘 파라미터로 객체를 받을 수 있지만
                        // 테스트 코드에서는 get 요청으로 객체를 보낼 수 없어 쿼리 매개변수 사용
                        .param("name", searchSpec.getName()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(searchSpec.getName()));
    }

    @Test
    @DisplayName(value = "회원 조회 실패 테스트")
    public void findListFailTest() throws Exception {
        MemberSearchSpecRequest searchSpec = new MemberSearchSpecRequest();
        searchSpec.setName("myName");

        doThrow(new NotFoundMemberException("검색 조건에 해당하는 회원을 찾을 수 없습니다")).when(memberService).findList(searchSpec);

        mvc.perform(MockMvcRequestBuilders.get("/members")
                        .param("name", searchSpec.getName()))
                .andExpect(status().isNotFound());
    }

    /*
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

     */
}