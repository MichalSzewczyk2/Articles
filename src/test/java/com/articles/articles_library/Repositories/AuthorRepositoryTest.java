package com.articles.articles_library.Repositories;

import com.articles.articles_library.DTOS.NewArticleModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void getAllAuthors() {
        Assertions.assertDoesNotThrow(()->authorRepository.getAllAuthors());
    }

    @Test
    void getAuthorById_idEqals0() {
        //given
        int id  = 0;
        //then
        Assertions.assertThrows(IllegalArgumentException.class, ()->authorRepository.getAuthorById(id));
    }

    @Test
    void getAuthorById_idLessThan0() {
        //given
        int id  = -10;
        //then
        Assertions.assertThrows(IllegalArgumentException.class, ()->authorRepository.getAuthorById(id));
    }
    @Test
    void addAuthor() {

        //given
        var service = new NewArticleModel(null, null, null,null,null,null );
        //then
        Assertions.assertThrows(Exception.class, ()->authorRepository.addAuthor(service));

    }
}