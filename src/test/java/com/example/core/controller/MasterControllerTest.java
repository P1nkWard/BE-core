package com.example.core.controller;

import com.example.core.document.config.RestDocsTestSupport;
import com.example.core.member.controller.MasterController;
import com.example.core.member.domain.Address;
import com.example.core.member.domain.Phone;
import com.example.core.member.dto.MasterDto;
import com.example.core.member.dto.MemberDto;
import com.example.core.member.service.MasterDefaultService;
import com.example.core.member.service.MasterService;
import com.example.core.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = MasterController.class)
public class MasterControllerTest {
    @Autowired
    private MockMvc mvc;
    private static ObjectMapper objectMapper = new ObjectMapper();
    @MockBean
    private MasterService masterService;
    @Test
    @DisplayName(value = "사장님 회원가입 테스트")
    public void registerMasterTest() throws Exception {
        // body 데이터
        Phone phone = new Phone("010", "9948", "1901");
        Address address = new Address("대한민국", "동일로", "802호");
        MasterDto master = new MasterDto();
        master.setId("sibjagun");
        master.setPw("12345");
        master.setName("서상현");
        master.setPhone(phone);
        master.setAddress(address);
        String requestJson = objectMapper.writeValueAsString(master);
        mvc.perform(MockMvcRequestBuilders.post("/master/register")
                        .content(requestJson)
                        .header("referer", "http://localhost:8080/home")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(master.getId()))
                .andExpect(header().string("referer", "http://localhost:8080/home"))
                .andDo(print());
    }


}
