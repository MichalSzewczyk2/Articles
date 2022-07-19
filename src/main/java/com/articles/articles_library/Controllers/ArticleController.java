package com.articles.articles_library.Controllers;

import com.articles.articles_library.DTOS.ArticleModel;
import com.articles.articles_library.Repositories.ArticleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

    final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/allArticle")
    public Iterable<ArticleModel> getAllArticles() {
        return articleRepository.getAllArticles();
    }
}
