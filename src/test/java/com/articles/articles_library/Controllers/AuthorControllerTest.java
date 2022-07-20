package com.articles.articles_library.Controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthorControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getAllAuthorsEnd() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/allAuthors", String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void getAuthor() {
        int id = 1;

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/Author/" + id, String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}