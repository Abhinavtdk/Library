package com.example.library.feign;

import com.example.library.model.Book;
import com.example.library.model.response.SearchBooksListResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "book-service", url = "https://www.googleapis.com/books/v1/volumes")
public interface BookFeignClient {

    @GetMapping("/{volumeId}")
    Book getBookByVolumeId(@PathVariable String volumeId,
                           @RequestParam String key);

    @GetMapping("/")
    SearchBooksListResponse searchBooks(@RequestParam String key,
                                        @RequestParam String q,
                                        @RequestParam Integer startIndex,
                                        @RequestParam Integer maxResults,
                                        @RequestParam String orderBy);

}
