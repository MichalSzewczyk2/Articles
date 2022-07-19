package com.articles.articles_library.Interfaces;

import com.articles.articles_library.DTOS.ArticleModel;

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

}
