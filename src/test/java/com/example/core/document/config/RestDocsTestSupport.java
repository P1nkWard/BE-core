package com.example.core.document.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@ExtendWith({RestDocumentationExtension.class, MockitoExtension.class})
public abstract class RestDocsTestSupport {
    protected MockMvc mvc;
    protected ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    protected RestDocumentationResultHandler restDocs;

    @BeforeEach
    public void setup(final WebApplicationContext context,
                      final RestDocumentationContextProvider provider) {
        this.mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(MockMvcRestDocumentation.documentationConfiguration(provider))
                .alwaysDo(MockMvcResultHandlers.print())
                .alwaysDo(restDocs)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }
    // 이부분 해석해서 올리고
    // 이부분을 왜 이렇게 바꿔야하는지
    // 왜 이렇게 했어야했고 어떻게 바꿔야하는지
    // 다시 정리해서 올리기
}