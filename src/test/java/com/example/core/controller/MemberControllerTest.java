package com.example.core.controller;

import com.example.core.MemberDto;
import com.example.core.document.config.RestDocsTestSupport;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MemberControllerTest extends RestDocsTestSupport {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName(value = "회원 생성 테스트")
    public void registerMemberTest() throws Exception {
        // body 데이터
        MemberDto member = new MemberDto();
        member.setId("abc");
        member.setPw("123");
        member.setName("myName");
        member.setPhone("010-0000-0000");
        member.setAddress("myAddress");
        String requestJson = objectMapper.writeValueAsString(member);

        mvc.perform(MockMvcRequestBuilders.post("/member")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("회원 등록 성공"))
                .andDo(print());
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
        String idString = String.join(",", ids);

        mvc.perform(MockMvcRequestBuilders.get("/member/{ids}", idString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("id_1"))
                .andExpect(jsonPath("$[0].pw").value("123"))
                .andExpect(jsonPath("$[0].name").value("myName"))
                .andExpect(jsonPath("$[0].phone").value("010-0000-0000"))
                .andExpect(jsonPath("$[0].address").value("myAddress"))
                .andExpect(jsonPath("$[1].id").value("id_2"))
                .andDo(print());
    }

    @Test
    @DisplayName(value = "회원 수정 테스트")
    public void ModifyMemberTest() throws Exception {
        // body 데이터
        MemberDto member = new MemberDto();
        member.setId("qwer");
        member.setPw("1234");
        member.setName("newName");
        member.setPhone("010-1111-1111");
        member.setAddress("newAddress");
        String requestJson = objectMapper.writeValueAsString(member);

        mvc.perform(MockMvcRequestBuilders.put("/member")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("회원 정보 수정 성공"))
                .andDo(print());
    }

    @Test
    @DisplayName(value = "회원 삭제 테스트")
    public void deleteMemberTest() throws Exception {
        List<String> ids = new ArrayList<>();
        ids.add("id_1");
        ids.add("id_2");
        ids.add("id_3");
        String idString = String.join(",", ids);

        mvc.perform(MockMvcRequestBuilders.delete("/member/{ids}", idString))
                .andExpect(status().isOk())
                .andExpect(content().string("회원 삭제 성공"))
                .andDo(print());
    }

    @Test
    @DisplayName(value = "회원 id 찾기 테스트")
    public void findMemberIdTest() throws Exception {
        // body 데이터
        MemberDto member = new MemberDto();
        member.setName("myName");
        member.setPhone("010-0000-0000");
        String requestJson = objectMapper.writeValueAsString(member);

        mvc.perform(MockMvcRequestBuilders.post("/member/find-id")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("찾은 아이디"))
                .andDo(print());
    }

    @Test
    @DisplayName(value = "회원 pw 찾기 테스트")
    public void findMemberPwTest() throws Exception {
        // body 데이터
        MemberDto member = new MemberDto();
        member.setId("abc");
        member.setPhone("010-0000-0000");
        String requestJson = objectMapper.writeValueAsString(member);

        mvc.perform(MockMvcRequestBuilders.post("/member/find-pw")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("찾은 비밀번호"))
                .andDo(print());
    }

//    @Test
//    @DisplayName(value = "로그인 테스트")
//    public void loginTest() throws Exception {
//        // body 데이터
//        MemberDto member = new MemberDto();
//        member.setId("abc");
//        member.setPw("123");
//        member.setName("myName");
//        member.setPhone("010-0000-0000");
//        member.setAddress("myAddress");
//        String requestJson = objectMapper.writeValueAsString(member);
//
//        mvc.perform(MockMvcRequestBuilders.post("/login")
//                        .content(requestJson))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
}