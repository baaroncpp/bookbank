package com.bkbwongo.bookbank.service.imp;

import com.bkbwongo.bookbank.commons.exceptions.BadRequestException;
import com.bkbwongo.bookbank.constants.enums.Lang;
import com.bkbwongo.bookbank.dto.models.BookDto;
import com.bkbwongo.bookbank.dto.models.TopicDto;
import com.bkbwongo.bookbank.dto.service.DtoService;
import com.bkbwongo.bookbank.dto.service.imp.DtoServiceImp;
import com.bkbwongo.bookbank.jpa.models.Book;
import com.bkbwongo.bookbank.jpa.models.Topic;
import com.bkbwongo.bookbank.repository.BookRepository;
import com.bkbwongo.bookbank.repository.TopicRepository;
import com.bkbwongo.bookbank.service.BookBankService;
import com.bkbwongo.bookbank.utils.AppUtilities;
import com.bkbwongo.bookbank.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author bkaaron
 * @created 06/02/2022 - 9:31 AM
 * @project bookbank
 */
@Slf4j
@Service
public class BookBankServiceImp implements BookBankService {

    private static final String SHA = "SHA-1";
    private static final String MD5 = "MD5";

    private BookRepository bookRepository;
    private TopicRepository topicRepository;
    private DtoService dtoService;

    @Autowired
    public BookBankServiceImp(BookRepository bookRepository, TopicRepository topicRepository, DtoService dtoService) {
        this.bookRepository = bookRepository;
        this.topicRepository = topicRepository;
        this.dtoService = dtoService;
    }

    @Override
    public BookDto addBook(MultipartFile file, BookDto bookDto) throws IOException, NoSuchAlgorithmException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        if(fileName.contains("..")) {
            throw new BadRequestException("Sorry! Filename contains invalid path sequence " + fileName);
        }

        byte[] bytes = new byte[0];
        bytes = file.getBytes();

        String md5 = AppUtilities.getFileCheckSum(bytes, MD5);
        String sha_1 = AppUtilities.getFileCheckSum(bytes, SHA);
        Long fileSize = file.getSize();
        String fileExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String originalName = file.getOriginalFilename();

        var bookVar = bookRepository.findByMd5IgnoreCase(md5);
        if(bookVar.isPresent()){
            throw new BadRequestException(String.format("MD5 %s already exists", md5));
        }

        Book book = dtoService.convertDTOToBook(bookDto);
        book.setMd5(md5);
        book.setSha1(sha_1);
        book.setFileSize(fileSize);
        book.setFileExtension(fileExtension);
        book.setOriginalFileName(originalName);
        book.setFilePath(FileUtils.folderPath);
        book.setFileName(md5.toLowerCase());
        book.setLanguage(Lang.en.getValue());
        book.setCreatedOn(new Date());

        FileUtils.storeFile(bytes, md5.toLowerCase());

        return dtoService.convertBookToDto(bookRepository.save(book));
    }

    @Override
    public BookDto getBookById(Long id) {
        return dtoService.convertBookToDto(bookRepository.findById(id).get());
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> findBooksByTitle(String title) {
        return null;
    }

    @Override
    public TopicDto addTopic(TopicDto topicDto) {
        Topic topic = dtoService.convertDTOToTopic(topicDto);
        topic.setCreatedOn(new Date());

        if(topicRepository.existsByName(topicDto.getName())){
            throw new BadRequestException(String.format("Topic %s already exists", topicDto.getName()));
        }

        return dtoService.convertTopicToDTO(topicRepository.save(topic));
    }

    @Override
    public List<TopicDto> getTopics() {
        return topicRepository.findAll().stream()
                .map(topic -> dtoService.convertTopicToDTO(topic))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTopic(Long id) {
        topicRepository.deleteById(id);
        log.info("topic deleted");
    }

}
