package com.bkbwongo.bookbank.dto.service.imp;

import com.bkbwongo.bookbank.commons.exceptions.BadRequestException;
import com.bkbwongo.bookbank.dto.models.BookDto;
import com.bkbwongo.bookbank.dto.models.TopicDto;
import com.bkbwongo.bookbank.dto.service.DtoService;
import com.bkbwongo.bookbank.jpa.models.Book;
import com.bkbwongo.bookbank.jpa.models.Topic;
import com.bkbwongo.bookbank.repository.TopicRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author bkaaron
 * @created 04/02/2022 - 11:07 AM
 * @project bookbank
 */
@Component
public class DtoServiceImp implements DtoService{

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TopicDto convertTopicToDTO(Topic topic){
        return modelMapper.map(topic, TopicDto.class);
    }

    @Override
    public Topic convertDTOToTopic(TopicDto topicDto){
        return modelMapper.map(topicDto, Topic.class);
    }

    @Override
    public BookDto convertBookToDto(Book book){
        var bookDto = modelMapper.map(book, BookDto.class);
        bookDto.setTopicId(book.getTopic().getId());
        return bookDto;
    }

    @Override
    public Book convertDTOToBook(BookDto bookDto){
        var book =  modelMapper.map(bookDto, Book.class);

        Optional<Topic> topic = topicRepository.findById(bookDto.getTopicId());
        if(!topic.isPresent()){
            throw new BadRequestException(String.format("Topic with ID %s does not exist", bookDto.getTopicId()));
        }

        book.setTopic(topic.get());
        return book;
    }


}
