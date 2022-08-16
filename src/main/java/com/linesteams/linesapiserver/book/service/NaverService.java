package com.linesteams.linesapiserver.book.service;

import com.linesteams.linesapiserver.book.dto.BookInfoDto;
import com.linesteams.linesapiserver.book.dto.BookInfoItemDto;
import com.linesteams.linesapiserver.config.AppConfig;
import lombok.SneakyThrows;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class NaverService {
    public static final String X_NAVER_CLIENT_ID = "X-Naver-Client-Id";
    public static final String X_NAVER_CLIENT_SECRET = "X-Naver-Client-Secret";
    public static final String SEARCH_BOOK_PATH = "/v1/search/book.json";

    private final AppConfig appConfig;
    private final RestTemplate restTemplate;
    private final URI naverUri;

    @SneakyThrows
    public NaverService(AppConfig appConfig, RestTemplate restTemplate) {
        this.appConfig = appConfig;
        this.restTemplate = restTemplate;
        this.naverUri = new URI(appConfig.getNaver().getUrl());
    }

    @SneakyThrows
    public BookInfoItemDto getBookByIsbn(String isbn) {
        URI uri = new URIBuilder(naverUri)
                .setPath(SEARCH_BOOK_PATH)
                .addParameter("query", isbn)
                .build();

        ResponseEntity<BookInfoDto> response = restTemplate.exchange(uri, HttpMethod.GET, createHttpEntity(""), BookInfoDto.class);

        if (response.getStatusCode().isError() || response.getBody() == null || response.getBody().getItems().isEmpty()) {
            return null;
        }

        return response.getBody().getItems().get(0);
    }

    private <T> HttpEntity createHttpEntity(T body) {
        return new HttpEntity<>(body, createHeader());
    }

    private HttpHeaders createHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set(X_NAVER_CLIENT_ID, appConfig.getNaver().getClientId());
        httpHeaders.set(X_NAVER_CLIENT_SECRET, appConfig.getNaver().getClientSecret());

        return httpHeaders;
    }
}
