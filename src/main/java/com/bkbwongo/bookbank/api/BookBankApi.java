package com.bkbwongo.bookbank.api;

import com.bkbwongo.bookbank.dto.models.BookDto;
import com.bkbwongo.bookbank.dto.models.TopicDto;
import com.bkbwongo.bookbank.service.BookBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * @author bkaaron
 * @created 04/02/2022 - 12:59 PM
 * @project bookbank
 */
@RestController
@RequestMapping("api")
public class BookBankApi {

    private static final String APPLICATION_JSON = "application/json";

    @Autowired
    private BookBankService bookBankService;

    @PostMapping(path = "topic",consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public ResponseEntity<Object> addTopic(@RequestBody TopicDto topicDto){
        return ResponseEntity.ok(bookBankService.addTopic(topicDto));
    }

    @GetMapping(path = "topics", produces = APPLICATION_JSON)
    public ResponseEntity<Object> getTopic(){
        return ResponseEntity.ok(bookBankService.getTopics());
    }

    @GetMapping(path = "books/{title}", produces = APPLICATION_JSON)
    public ResponseEntity<Object> getBooksByTitle(@PathVariable("title") String title){
        return ResponseEntity.ok(bookBankService.findBooksByTitle(title));
    }

    @GetMapping(path = "book/{id}", produces = APPLICATION_JSON)
    public ResponseEntity<Object> getBooksById(@PathVariable("id") Long id){
        return ResponseEntity.ok(bookBankService.getBookById(id));
    }

    @DeleteMapping(path = "book/{id}")
    public void deleteBookById(@PathVariable("id") Long id){
        bookBankService.deleteBookById(id);
    }

    @PostMapping(path = "book", /*params = {"file","title","author","year","publisher","publisher","edition","topicId","description"},*/ produces = APPLICATION_JSON)
    public ResponseEntity<Object> addBook(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("year") String year,
            @RequestParam("publisher") String publisher,
            @RequestParam("edition") String edition,
            @RequestParam("topicId") Long topicId,
            @RequestParam("description") String description
    ) throws IOException, NoSuchAlgorithmException {

        BookDto bookDto = new BookDto();
        bookDto.setTitle(title);
        bookDto.setAuthor(author);
        bookDto.setYear(year);
        bookDto.setPublisher(publisher);
        bookDto.setEdition(edition);
        bookDto.setTopicId(topicId);
        bookDto.setDescription(description);

        return ResponseEntity.ok(bookBankService.addBook(file, bookDto));
    }

}
