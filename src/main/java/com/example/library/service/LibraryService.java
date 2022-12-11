package com.example.library.service;

import com.example.library.feign.BookFeignClient;
import com.example.library.model.Book;
import com.example.library.model.response.SearchBooksListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class LibraryService {

    @Value("${apiKey}")
    private String apiKey;
    @Value("${titleFilter}")
    private String titleFilter;
    @Value("${authorFilter}")
    private String authorFilter;
    @Value("${publisherFilter}")
    private String publisherFilter;
    @Value("${subjectFilter}")
    private String subjectFilter;
    @Value("${isbnFilter}")
    private String isbnFilter;
    @Value("${lccnFilter}")
    private String lccnFilter;
    @Value("${oclcFilter}")
    private String oclcFilter;
    @Value("${acceptedOrderByFilters}")
    private List<String> acceptedOrderByFilters;
    @Autowired
    private BookFeignClient bookFeignClient;

    public Book getBookByVolumeId(String volumeId){
        return bookFeignClient.getBookByVolumeId(volumeId, apiKey);
    }

    private String getFinalQuery(String searchQuery,
                                 String title,
                                 String author,
                                 String publisher,
                                 String subject,
                                 String isbn,
                                 String lccn,
                                 String oclc){
        String parameterQueries = "";
        if(StringUtils.hasText(title)){
            String titleFilterQuery = titleFilter + title;
            parameterQueries += "+" + titleFilterQuery;
        }
        if(StringUtils.hasText(author)){
            String authorFilterQuery = authorFilter + author;
            parameterQueries += "+" + authorFilterQuery;
        }
        if(StringUtils.hasText(publisher)){
            String publisherFilterQuery = publisherFilter + publisher;
            parameterQueries += "+" +publisherFilterQuery;
        }
        if(StringUtils.hasText(subject)){
            String subjectFilterQuery = subjectFilter + subject;
            parameterQueries += "+" +subjectFilterQuery;
        }
        if(StringUtils.hasText(isbn)){
            String isbnFilterQuery = isbnFilter + title;
            parameterQueries += "+" +isbnFilterQuery;
        }
        if(StringUtils.hasText(lccn)){
            String lccnFilterQuery = lccnFilter + title;
            parameterQueries += "+" +lccnFilterQuery;
        }
        if(StringUtils.hasText(oclc)){
            String oclcFilterQuery = oclcFilter + title;
            parameterQueries += "+" +oclcFilterQuery;
        }

        if(!StringUtils.hasText(searchQuery) && StringUtils.hasText(parameterQueries)){
            parameterQueries = parameterQueries.substring(1);
        }else if(StringUtils.hasText(searchQuery)){
            parameterQueries = searchQuery + parameterQueries;
        }
        return parameterQueries;
    }

    public SearchBooksListResponse getBooks(String searchQuery,
                                            String title,
                                            String author,
                                            String publisher,
                                            String subject,
                                            String isbn,
                                            String lccn,
                                            String oclc,
                                            Integer page,
                                            Integer maxResults,
                                            String orderBy){

        String finalQuery = getFinalQuery(searchQuery,
                title,
                author,
                publisher,
                subject,
                isbn,
                lccn,
                oclc);
        int startIndex = findStartIndex(page, maxResults);

        if(!acceptedOrderByFilters.contains(orderBy)){
            //throw an error
        }

        return bookFeignClient.searchBooks(apiKey, finalQuery, startIndex, maxResults, orderBy);

    }

    private Integer findStartIndex(Integer page, Integer maxResults) {
        /*Add a check for page number and maxResults, if it isn’t greater than totalItems.
          Page Number 0 should also give an error.
          Is it fine to make a dummy query at the start to find the total items in result of search query?
          For now, I’ve only implemented the page number functionality without this check.*/
        int startIndex = (page-1)*maxResults;
        return startIndex;
    }

}
