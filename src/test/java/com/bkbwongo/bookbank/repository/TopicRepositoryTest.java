package com.bkbwongo.bookbank.repository;

import com.bkbwongo.bookbank.jpa.models.Topic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @Project
 * @Author bkaaron
 * @Date 20/03/2022 18:21
 **/
@DataJpaTest
class TopicRepositoryTest {

    @Autowired
    private TopicRepository underTest;

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }

    @Test
    void itShouldFindTopicByName(){
        //given
        String topicName = "Social Sciences";

        Topic topic = new Topic();
        topic.setName(topicName);
        topic.setDescription("This topic groups social science books");
        topic.setCreatedOn(new Date());
        topic.setModifiedOn(new Date());

        underTest.save(topic);

        //when
        Optional<Topic> expected = underTest.findByName(topicName);

        //then
        assertThat(expected.get().getName()).isEqualTo(topicName);
    }
}