package com.articles.articles_library.Interfaces;

import com.articles.articles_library.DTOS.ArticleModel;
import com.articles.articles_library.DTOS.AutorModel;
import com.articles.articles_library.DTOS.ContentModel;
import com.articles.articles_library.DTOS.NewArticleModel;

import java.util.List;

public interface IArticle {

    static List<ArticleModel> getAllArticles(){
        return null;
    }

    static ArticleModel getArticleById(int id){
        return null;
    };

    static List<ArticleModel> getArticleByWord(String word){
        return null;
    }

    static void deleteArticleById(int id) {}

    static void newArticle(NewArticleModel model) {}

    void updateArticle(ArticleModel model);
}
