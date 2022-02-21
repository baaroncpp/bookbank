package com.bkbwongo.bookbank.service.imp;

import com.bkbwongo.bookbank.commons.exceptions.BadRequestException;
import com.bkbwongo.bookbank.dto.models.BookDto;
import com.bkbwongo.bookbank.dto.models.SearchDto;
import com.bkbwongo.bookbank.dto.service.DtoService;
import com.bkbwongo.bookbank.repository.BookRepository;
import com.bkbwongo.bookbank.repository.TopicRepository;
import com.bkbwongo.bookbank.service.SearchService;
import com.bkbwongo.bookbank.utils.AppUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bkaaron
 * @created 20/02/2022 - 11:58 AM
 * @project bookbank
 */
@Service
public class SearchServiceImp implements SearchService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private DtoService dtoService;

    @Override
    public List<BookDto> makeSearch(SearchDto searchDto, Pageable pageable) {

        String searchValue = searchDto.getSearch();

        if(searchDto.isAuthor()){
            return authorSearch(searchValue, pageable);
        }

        if(searchDto.isTitle()){
            return titleSearch(searchValue, pageable);
        }

        if(searchDto.isPublisher()){
            return publisherSearch(searchValue, pageable);
        }

        if(searchDto.isTopic()){
            return topicSearch(AppUtilities.stringToLong(searchValue), pageable);
        }

        if(searchDto.isYear()){
            return yearSearch(searchValue, pageable);
        }

        if(searchDto.isMd5()){
            return md5Search(searchValue);
        }

        return null;
    }

    @Override
    public BookDto getBookById(Long id) {
        var book = bookRepository.findById(id).orElseThrow(
                () -> new BadRequestException(String.format("File with ID %s does not exist"))
        );
        return dtoService.convertBookToDto(book);
    }

    private List<BookDto> topicSearch(Long topicId, Pageable pageable) {

        var topic = topicRepository.findById(topicId).orElseThrow(
                () -> new BadRequestException(String.format("Topic with ID %s does not exist"))
        );
        return bookRepository.findByTopic(topic, pageable).stream()
                .map(book -> dtoService.convertBookToDto(book))
                .collect(Collectors.toList());
    }

    private List<BookDto> md5Search(String md5) {
        return bookRepository.findByMd5ContainingIgnoreCase(md5).stream()
                .map(book -> dtoService.convertBookToDto(book))
                .collect(Collectors.toList());
    }

    private List<BookDto> titleSearch(String title, Pageable pageable) {
        return bookRepository.findByTitleContainingIgnoreCase(title, pageable).stream()
                .map(book -> dtoService.convertBookToDto(book))
                .collect(Collectors.toList());
    }

    private List<BookDto> authorSearch(String author, Pageable pageable) {
        return bookRepository.findByAuthorContainingIgnoreCase(author, pageable).stream()
                .map(book -> dtoService.convertBookToDto(book))
                .collect(Collectors.toList());
    }

    private List<BookDto> yearSearch(String year, Pageable pageable) {
        return bookRepository.findByYearContainingIgnoreCase(year, pageable).stream()
                .map(book -> dtoService.convertBookToDto(book))
                .collect(Collectors.toList());
    }

    private List<BookDto> publisherSearch(String publisher, Pageable pageable) {
        return bookRepository.findByPublisherContainingIgnoreCase(publisher, pageable).stream()
                .map(book -> dtoService.convertBookToDto(book))
                .collect(Collectors.toList());
    }

}
