package com.bkbwongo.bookbank.dto.service;

import com.bkbwongo.bookbank.dto.models.BookDto;
import com.bkbwongo.bookbank.dto.models.TopicDto;
import com.bkbwongo.bookbank.jpa.models.Book;
import com.bkbwongo.bookbank.jpa.models.Topic;

/**
 * @Project
 * @Author bkaaron
 * @Date 21/03/2022 11:32
 **/
public interface DtoService {
    TopicDto convertTopicToDTO(Topic topic);
    Topic convertDTOToTopic(TopicDto topicDto);
    BookDto convertBookToDto(Book book);
    Book convertDTOToBook(BookDto bookDto);
}
