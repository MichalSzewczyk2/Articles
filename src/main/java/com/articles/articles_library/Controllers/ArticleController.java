package com.articles.articles_library.Controllers;

import com.articles.articles_library.DTOS.ArticleModel;
import com.articles.articles_library.Repositories.ArticleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/Article/{id}", method = RequestMethod.GET)
    public ArticleModel getArticle(@PathVariable int id) {
        return articleRepository.getArticleById(id);
    }

    @PostMapping("/ArticleWord")
    public Iterable<ArticleModel> getArticleByWord(@RequestBody String word) {
        System.out.println(word);
        return articleRepository.getArticleByWord(word);
    }
}
