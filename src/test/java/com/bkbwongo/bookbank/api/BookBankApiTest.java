package com.bkbwongo.bookbank.api;

import com.bkbwongo.bookbank.dto.models.TopicDto;
import com.bkbwongo.bookbank.repository.TopicRepository;
import com.bkbwongo.bookbank.service.BookBankService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Project
 * @Author bkaaron
 * @Date 23/03/2022 13:23
 **/

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@TestPropertySource(locations = "classpath:applicationintegrationtest.yml")
class BookBankApiTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BookBankService bookBankService;

    @Autowired
    private TopicRepository topicRepository;

    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        //topicRepository.deleteAll();
        mapper = new ObjectMapper();
    }

    @Test
    public void givenTopics_whenGetTopics_thenStatus200() throws Exception {

        TopicDto topic = new TopicDto();
        topic.setName("Test topics 4");
        topic.setDescription("test description for a test project");

        bookBankService.addTopic(topic);

        mvc.perform(get("/api/v1/topics"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
                //.andExpect(jsonPath("[0].name", is()));

    }

    @Test
    public void givenTopic_addTopic_thenStatus200() throws Exception {

        TopicDto topic = new TopicDto();
        topic.setName("Test topics 87");
        topic.setDescription("test description for a test project");

        mvc.perform(post("/api/v1/topic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(topic))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        //.andExpect(jsonPath("[0].name", is()));

    }
}