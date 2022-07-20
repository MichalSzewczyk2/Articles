package com.articles.articles_library.Controllers;

import com.articles.articles_library.DTOS.ArticleModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ArticleControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getAllArticles() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/allArticles", String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void getArticle() {
        int id  = 1;

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/Article/" + id, String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void getArticleByWord() {
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/ArticleWord", "word", String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void deleteArticle() {
        int id = 100;
        String url = "http://localhost:" + port + "/DeleteArticle/" + id;
        ResponseEntity<Void>  response = restTemplate.exchange(url, HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

    }

    @Test
    void addArticle() {
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/ArticleWord", "word", String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void updateArticle() throws JsonProcessingException {

        String url = "http://localhost:" + port + "/updateArticle";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(null,headers);
        ResponseEntity<String>  response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}