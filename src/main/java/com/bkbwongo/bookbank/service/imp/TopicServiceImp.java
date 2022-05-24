package com.bkbwongo.bookbank.service.imp;

import com.bkbwongo.bookbank.commons.exceptions.BadRequestException;
import com.bkbwongo.bookbank.jpa.models.Topic;
import com.bkbwongo.bookbank.repository.TopicRepository;
import com.bkbwongo.bookbank.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Project
 * @Author bkaaron
 * @Date 21/03/2022 16:07
 **/
@Service
public class TopicServiceImp implements TopicService {

    private TopicRepository topicRepository;

    @Autowired
    public TopicServiceImp(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public Topic addTopic(Topic topic) {
        topic.setCreatedOn(new Date());

        if(topicRepository.existsByName(topic.getName())){
            throw new BadRequestException(String.format("Topic %s already exists", topic.getName()));
        }

        return topicRepository.save(topic);
    }

    @Override
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }
}
