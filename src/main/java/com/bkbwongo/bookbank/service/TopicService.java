package com.bkbwongo.bookbank.service;

import com.bkbwongo.bookbank.jpa.models.Topic;

import java.util.List;

/**
 * @Project
 * @Author bkaaron
 * @Date 21/03/2022 16:06
 **/
public interface TopicService {
    Topic addTopic(Topic topic);
    List<Topic> getAllTopics();
}
