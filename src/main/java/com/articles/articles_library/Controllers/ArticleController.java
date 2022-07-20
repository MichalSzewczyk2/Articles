package com.articles.articles_library.Controllers;

import com.articles.articles_library.DTOS.ArticleModel;
import com.articles.articles_library.DTOS.NewArticleModel;
import com.articles.articles_library.Repositories.ArticleRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleController {

    final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/allArticles")
    public Iterable<ArticleModel> getAllArticles() {
        return articleRepository.getAllArticles();
    }

    @RequestMapping(value = "/Article/{id}", method = RequestMethod.GET)
    public ArticleModel getArticle(@PathVariable int id) {
        return articleRepository.getArticleById(id);
    }

    @PostMapping("/ArticleWord")
    public Iterable<ArticleModel> getArticleByWord(@RequestBody String word) {
        return articleRepository.getArticleByWord(word);
    }

    @RequestMapping(value = "/DeleteArticle/{id}", method = RequestMethod.DELETE)
    public void deleteArticle(@PathVariable int id) {
        articleRepository.deleteArticleById(id);
    }

    @PostMapping("/addArticle")
    public void addArticle(@RequestBody NewArticleModel model){
        articleRepository.newArticle(model);
    }
    @PutMapping("/updateArticle")
    public void updateArticle(@RequestBody ArticleModel model){
        articleRepository.updateArticle(model);
    }
}
