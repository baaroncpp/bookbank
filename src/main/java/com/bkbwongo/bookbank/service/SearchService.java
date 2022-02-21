package com.bkbwongo.bookbank.service;

import com.bkbwongo.bookbank.dto.models.BookDto;
import com.bkbwongo.bookbank.dto.models.SearchDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author bkaaron
 * @created 20/02/2022 - 11:48 AM
 * @project bookbank
 */
public interface SearchService {
    List<BookDto> makeSearch(SearchDto searchDto, Pageable pageable);
    BookDto getBookById(Long id);
}
