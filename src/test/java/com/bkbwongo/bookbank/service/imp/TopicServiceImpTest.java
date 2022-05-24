package com.bkbwongo.bookbank.service.imp;

import com.bkbwongo.bookbank.commons.exceptions.BadRequestException;
import com.bkbwongo.bookbank.jpa.models.Topic;
import com.bkbwongo.bookbank.repository.TopicRepository;
import com.bkbwongo.bookbank.service.TopicService;
import com.sun.xml.bind.v2.TODO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * @Project
 * @Author bkaaron
 * @Date 21/03/2022 16:10
 **/
@ExtendWith(MockitoExtension.class)
class TopicServiceImpTest {

    @Mock private TopicRepository topicRepository;

    private TopicServiceImp underTest;

    @BeforeEach
    void setUp() {
        underTest = new TopicServiceImp(topicRepository);
    }

    @Test
    void canAddTopic() {
        //given
        Topic topic = new Topic();
        topic.setName("Arts");
        topic.setDescription("Having a topic test");
        topic.setCreatedOn(new Date());
        topic.setModifiedOn(new Date());

        //when
        underTest.addTopic(topic);

        //then
        ArgumentCaptor<Topic> argumentCaptor = ArgumentCaptor.forClass(Topic.class);
        verify(topicRepository).save(argumentCaptor.capture());

        Topic capturedTopic = argumentCaptor.getValue();

        assertThat(capturedTopic).isEqualTo(topic);
    }

    @Test
    void itWillThrowIfNameExists(){
        //given
        Topic topic = new Topic();
        topic.setName("Arts");
        topic.setDescription("Having a topic test");
        topic.setCreatedOn(new Date());
        topic.setModifiedOn(new Date());

        //when
        //then
        given(topicRepository.existsByName(topic.getName())).willReturn(Boolean.TRUE);

        assertThatThrownBy(() -> underTest.addTopic(topic))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining(String.format("Topic %s already exists", topic.getName()));

        verify(topicRepository, never()).save(any());
    }

    @Test
    void itShouldGetAllTopics() {
    }
}