package com.articles.articles_library.Repositories;

import com.articles.articles_library.DTOS.ArticleModel;
import com.articles.articles_library.DTOS.NewArticleModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;

    @Test
    void getAllArticles() {
        Assertions.assertDoesNotThrow(() -> articleRepository.getAllArticles());
    }

    @Test
    void getArticleById() {
        //given
        int id = 0;
        //then
        Assertions.assertThrows(Exception.class, ()->articleRepository.getArticleById(id));
    }

    @Test
    void getArticleByWord() {
        //given
        String word = "article";
        //then
        Assertions.assertDoesNotThrow(()->articleRepository.getArticleByWord(word));
    }

    @Test
    void deleteArticleById() {

        //given
        int id = 0;
        //then
        Assertions.assertThrows(Exception.class, ()->articleRepository.deleteArticleById(id));

    }

    @Test
    void newArticle() {

        //given
        var service = new NewArticleModel(null, null, null,null,null,null );
        //then
        Assertions.assertThrows(Exception.class, ()->articleRepository.newArticle(service));

    }

    @Test
    void updateArticle() {

        //given
        var service = new ArticleModel(0, null, null, null, null, null);
        //then
        Assertions.assertThrows(Exception.class, ()->articleRepository.updateArticle(service));

    }
}