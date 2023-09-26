package com.example.core.controller;

import com.example.core.document.config.RestDocsConfig;
import com.example.core.document.config.RestDocsTestSupport;
import com.example.core.member.controller.LoginApi;
import com.example.core.member.controller.dto.LoginDto;
import com.example.core.member.exception.InvalidCredentialsException;
import com.example.core.member.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoginApi.class)
@AutoConfigureRestDocs
@Import(RestDocsConfig.class)
public class LoginApiTest extends RestDocsTestSupport {
    @MockBean
    private LoginService loginService;

    private String id, pw, referer;

    @BeforeEach
    void setUp() {
        id = "abc";
        pw = "123";
        referer = "http://localhost:8080/home";
    }

    @Test
    @DisplayName(value = "로그인 성공 테스트")
    public void loginSuccessTest() throws Exception {
        doNothing().when(loginService).login(any(LoginDto.class));

        LoginDto dto = new LoginDto(id, pw);
        String requestJson = objectMapper.writeValueAsString(dto);

        mvc.perform(MockMvcRequestBuilders.post("/login")
                        .content(requestJson)
                        .header("referer", referer)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string("referer", referer))
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    @DisplayName(value = "로그인 실패 테스트 - 잘못된 아이디 또는 비밀번호")
    public void loginFailTestWithWrongIdOrPw() throws Exception {
        doThrow(new InvalidCredentialsException("아이디 또는 비밀번호가 잘못되었습니다")).when(loginService).login(any(LoginDto.class));

        LoginDto dto = new LoginDto(id, pw);
        String requestJson = objectMapper.writeValueAsString(dto);

        mvc.perform(MockMvcRequestBuilders.post("/login")
                        .content(requestJson)
                        .header("referer", referer)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName(value = "로그인 실패 테스트 - 유효하지 않은 입력값")
    public void loginFailTestWithInvalidInput() throws Exception {
        LoginDto dto = new LoginDto(id, "");
        String requestJson = objectMapper.writeValueAsString(dto);

        mvc.perform(MockMvcRequestBuilders.post("/login")
                        .content(requestJson)
                        .header("referer", referer)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
}
