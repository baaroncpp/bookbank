package com.bkbwongo.bookbank.api;

import com.bkbwongo.bookbank.dto.models.BookDto;
import com.bkbwongo.bookbank.dto.models.SearchDto;
import com.bkbwongo.bookbank.dto.models.SearchTemplateObject;
import com.bkbwongo.bookbank.jpa.models.Book;
import com.bkbwongo.bookbank.service.BookBankService;
import com.bkbwongo.bookbank.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bkaaron
 * @created 21/02/2022 - 9:59 AM
 * @project bookbank
 */
@Slf4j
@Controller
public class BookBankController {

    @Autowired
    private BookBankService bookBankService;

    @Autowired
    private SearchService searchService;

    private static final String SORT_ASC = "ASCENDING";
    private static final String SORT_BY = "createdOn";
    private static final String SEARCH_TEMPLATE_OBJECT = "searchTemplateObject";
    private static final String BOOKS = "books";
    private static final String SEARCH = "search";

    @GetMapping(value = "/")
    public String index(Model model) {

        SearchTemplateObject searchTemplateObject = new SearchTemplateObject();
        List<Book> books = new ArrayList<>();

        model.addAttribute(SEARCH_TEMPLATE_OBJECT, searchTemplateObject);
        model.addAttribute(BOOKS, books);

        return SEARCH;
    }

    @PostMapping(value = "/")
    public String getSearchObject(@ModelAttribute(SEARCH_TEMPLATE_OBJECT) SearchTemplateObject searchTemplateObject, Model model) {

        searchTemplateObject.validate();
        SearchDto search = createSearchDtoObject(searchTemplateObject);

        Pageable pageable = PageRequest.of(search.getPage(), search.getSize(), Sort.by(SORT_BY).ascending());
        model.addAttribute(BOOKS ,searchService.makeSearch(search, pageable));

        return SEARCH;
    }

    @GetMapping(path = "/download")
    @ResponseBody
    public ResponseEntity<Object> download(@Param(value = "id") Long id, HttpServletRequest request) throws IOException {

        BookDto book = searchService.getBookById(id);

        String fileBasePath = book.getFilePath();
        Path path = Paths.get(fileBasePath + book.getFileName());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.error("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+book.getOriginalFileName());
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(book.getFileSize())
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    private SearchDto createSearchDtoObject(SearchTemplateObject searchTemplateObject){

        String searchBy = searchTemplateObject.getSearchBy();

        SearchDto searchDto = new SearchDto();
        searchDto.setSearch(searchTemplateObject.getSearchValue());
        searchDto.setPage(searchTemplateObject.getPage());
        searchDto.setSize(searchTemplateObject.getSize());
        searchDto.setAuthor(Boolean.FALSE);
        searchDto.setTopic(Boolean.FALSE);
        searchDto.setTitle(Boolean.FALSE);
        searchDto.setMd5(Boolean.FALSE);
        searchDto.setPublisher(Boolean.FALSE);
        searchDto.setYear(Boolean.FALSE);
        searchDto.setDefaultSearch(Boolean.FALSE);
        searchDto.setSort(SORT_ASC);

        if(searchBy.equals("all")){
            searchDto.setTopic(Boolean.TRUE);
        }

        if(searchBy.equals("title")){
            searchDto.setTitle(Boolean.TRUE);
        }

        if(searchBy.equals("author")){
            searchDto.setAuthor(Boolean.TRUE);
        }

        if(searchBy.equals("year")){
            searchDto.setYear(Boolean.TRUE);
        }

        if(searchBy.equals("md5")){
            searchDto.setMd5(Boolean.TRUE);
        }

        return searchDto;
    }

}