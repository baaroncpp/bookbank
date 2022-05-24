package com.bkbwongo.bookbank.service.imp;

import com.bkbwongo.bookbank.commons.exceptions.BadRequestException;
import com.bkbwongo.bookbank.dto.models.BookDto;
import com.bkbwongo.bookbank.dto.models.TopicDto;
import com.bkbwongo.bookbank.dto.service.DtoService;
import com.bkbwongo.bookbank.jpa.models.Book;
import com.bkbwongo.bookbank.jpa.models.Topic;
import com.bkbwongo.bookbank.repository.BookRepository;
import com.bkbwongo.bookbank.repository.TopicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * @Project
 * @Author bkaaron
 * @Date 20/03/2022 20:42
 **/
@ExtendWith(MockitoExtension.class)
class BookBankServiceImpTest {

    @Mock private TopicRepository topicRepository;
    @Mock private BookRepository bookRepository;
    @Mock private DtoService dtoService;

    private BookBankServiceImp underTest;

    @BeforeEach
    void setUp() {
        underTest = new BookBankServiceImp(bookRepository, topicRepository, dtoService);
    }

    @Test
    void itShouldGetBookById() {
        //given
        Long id = Long.valueOf(1);

        Book book = new Book();
        book.setId(id);
        book.setTitle("Test Book");
        book.setAuthor("Tester");

        BookDto bookDto = new BookDto();
        bookDto.setId(id);
        bookDto.setTitle("Test Book");
        bookDto.setAuthor("Tester");

        //when
        when(bookRepository.findById(id)).thenReturn(Optional.of(book));
        when(dtoService.convertBookToDto(book)).thenReturn(bookDto);

        //then
        BookDto expected = underTest.getBookById(id);
        assertThat(expected.getId()).isEqualTo(id);
        verify(bookRepository).findById(id);

    }

    /*@Test
    @Disabled
    void findBooksByTitle() {
    }*/

    @Test
    void itShouldAddTopic() {
        //given
        Topic topic = new Topic();
        topic.setName("Arts");
        topic.setDescription("A grouping os Arts books");

        TopicDto topicDto = new TopicDto();
        topicDto.setName("Arts");
        topicDto.setDescription("A grouping os Arts books");

        //when
        //define the behaviour of dtoService
        when(dtoService.convertDTOToTopic(topicDto)).thenReturn(topic);

        underTest.addTopic(topicDto);

        //then
        ArgumentCaptor<Topic> topicCaptor = ArgumentCaptor.forClass(Topic.class);
        verify(topicRepository).save(topicCaptor.capture());
        Topic capturedTopic = topicCaptor.getValue();

        assertThat(capturedTopic).isEqualTo(topic);
    }

    @Test
    void willThrowWhenNameIsTaken(){
        //given
        TopicDto topicDto = new TopicDto();
        topicDto.setName("Arts");
        topicDto.setDescription("A grouping os Arts books");

        Topic topic = new Topic();
        topic.setName("Arts");
        topic.setDescription("A grouping os Arts books");

        when(dtoService.convertDTOToTopic(topicDto)).thenReturn(topic);

        given(topicRepository.existsByName(topicDto.getName())).willReturn(Boolean.TRUE);

        //when
        //then
        assertThatThrownBy(() -> underTest.addTopic(topicDto))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining(String.format("Topic %s already exists", topicDto.getName()));

        verify(topicRepository, never()).save(any());
    }

    @Test
    void itShouldGetAllTopics() {
       //when
       underTest.getTopics();

       //then
       verify(topicRepository).findAll();
    }
}