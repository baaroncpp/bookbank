package com.bkbwongo.bookbank.service;

import com.bkbwongo.bookbank.dto.models.BookDto;
import com.bkbwongo.bookbank.dto.models.TopicDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author bkaaron
 * @created 06/02/2022 - 9:32 AM
 * @project bookbank
 */
public interface BookBankService {
    BookDto addBook(MultipartFile file, BookDto bookDto) throws IOException, NoSuchAlgorithmException;
    BookDto getBookById(Long id);
    void deleteBookById(Long id);
    List<BookDto> findBooksByTitle(String title);
    TopicDto addTopic(TopicDto topicDto);
    List<TopicDto> getTopics();
    void deleteTopic(Long id);
}
