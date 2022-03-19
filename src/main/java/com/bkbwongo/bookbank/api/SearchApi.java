package com.bkbwongo.bookbank.api;

import com.bkbwongo.bookbank.dto.models.BookDto;
import com.bkbwongo.bookbank.dto.models.SearchDto;
import com.bkbwongo.bookbank.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author bkaaron
 * @created 20/02/2022 - 11:47 AM
 * @project bookbank
 */
@Slf4j
@RestController
@RequestMapping("api")
public class SearchApi {

    private static final String APPLICATION_JSON = "application/json";

    private static final String SORT_DESC = "DESCENDING";
    private static final String SORT_ASC = "ASCENDING";
    private static final String SORT_BY = "createdOn";

    @Autowired
    private SearchService searchService;

    @PostMapping(path = "search", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public ResponseEntity<Object> search(@RequestBody SearchDto search) {

        search.validate();
        Pageable pageable = null;

        switch(search.getSort()) {
            case SORT_DESC :
                pageable = PageRequest.of(search.getPage(), search.getSize(), Sort.by(SORT_BY).descending());
                break;
            case SORT_ASC :
                pageable = PageRequest.of(search.getPage(), search.getSize(), Sort.by(SORT_BY).ascending());
                break;
            default :
                pageable = PageRequest.of(search.getPage(), search.getSize(), Sort.by(SORT_BY));
        }

        return ResponseEntity.ok(searchService.makeSearch(search, pageable));
    }

    @GetMapping(path = "/download/{id}")
    public ResponseEntity<Object> download(@PathVariable("id") Long id,  HttpServletRequest request) throws IOException {

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
}
