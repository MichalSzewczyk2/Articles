package com.articles.articles_library.Repositories;

import com.articles.articles_library.DTOS.ContentModel;
import com.articles.articles_library.DTOS.NewArticleModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ContentRepositoryTest {

    @Autowired
    ContentRepository contentRepository;


    @Test
    void getAllContents() {

        Assertions.assertDoesNotThrow(()-> contentRepository.getAllContents());

    }

    @Test
    void getContentById_idEquals0() {

        //given
        int id  = 0;
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> contentRepository.getContentById(id));
    }
    @Test
    void getContentById_idLessThan0() {

        //given
        int id  = -2;
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> contentRepository.getContentById(id));
    }

    @Test
    void addContent() {

        //given
        var service = new NewArticleModel(null, null, null,null,null,null );
        //then
        Assertions.assertThrows(Exception.class, ()->contentRepository.addContent(service));
    }
}