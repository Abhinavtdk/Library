package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.model.response.SearchBooksListResponse;
import com.example.library.service.LibraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
@Slf4j
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/books")
    public SearchBooksListResponse searchBooks(@RequestParam(required = false) String searchQuery,
                                               @RequestParam(required = false) String title,
                                               @RequestParam(required = false) String author,
                                               @RequestParam(required = false) String publisher,
                                               @RequestParam(required = false) String subject,
                                               @RequestParam(required = false) String isbn,
                                               @RequestParam(required = false) String lccn,
                                               @RequestParam(required = false) String oclc,
                                               @RequestParam(required = false, defaultValue = "1") Integer page,
                                               @RequestParam(required = false, defaultValue = "10") Integer maxResults,
                                               @RequestParam(required = false, defaultValue = "relevance") String orderBy){
        return libraryService.getBooks(searchQuery,
                title,
                author,
                publisher,
                subject,
                isbn,
                lccn,
                oclc,
                page,
                maxResults,
                orderBy);
    }

    @GetMapping("/volume/{volumeId}")
    public Book getBookByVolumeId(@PathVariable String volumeId){
        return libraryService.getBookByVolumeId(volumeId);
    }

}
