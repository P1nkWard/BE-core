package com.example.core.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentationConfigurer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureRestDocs
@AutoConfigureMockMvc
@ExtendWith({MockitoExtension.class, RestDocumentationExtension.class})
//@WebMvcTest
//@SpringBootTest
public class MemberControllerTest {
    static class RequestRegisterMember {
        String id;
        String pw;

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
    }

    private static ObjectMapper objectMapper = new ObjectMapper();
    private MockMvc mvc;

    @BeforeEach
    void setup(RestDocumentationContextProvider restDocumentation){
        mvc = MockMvcBuilders
                .standaloneSetup(MemberController.class)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }



    @Test
    @DisplayName(value = "멤버 회원가입 테스트")
    public void member_register_test() throws Exception {
        //given
        RequestRegisterMember member = new RequestRegisterMember();
        member.id = "qotndk";
        member.pw = "qotndk";
        //pw를 encode 해가지고 처리한다던지.
        String requestJson = objectMapper.writeValueAsString(member);

        mvc.perform(MockMvcRequestBuilders.post("/register")
                        .content(requestJson))
                .andExpect(status().isOk())
                .andDo(
                        document("members/register",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint())
                        )
                );

    }
}
