package com.bkbwongo.bookbank.api;

import com.bkbwongo.bookbank.service.WelcomeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Project
 * @Author bkaaron
 * @Date 22/03/2022 11:25
 **/
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@TestPropertySource(locations = "classpath:applicationintegrationtest.yml")
class WelcomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private WelcomeService welcomeService;

    @Test
    void shouldGetDefaultWelcomeMessage() throws Exception {

        String expected = "Welcome Stranger";

        //set behaviour for welcome service
        when(welcomeService
                .getWelcomeMessage("Stranger"))
                .thenReturn("Welcome Stranger");

        mockMvc.perform(get("/welcome"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(( content().string(expected)));

        verify(welcomeService).getWelcomeMessage("Stranger");
    }
}