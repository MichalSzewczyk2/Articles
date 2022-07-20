package com.articles.articles_library.Interfaces;

import com.articles.articles_library.DTOS.AutorModel;
import com.articles.articles_library.DTOS.ContentModel;
import com.articles.articles_library.DTOS.NewArticleModel;

import java.util.List;

public interface IContent {

    static List<ContentModel> getAllContents() {
        return null;
    }
    static ContentModel getContentById(int id) {
        return null;
    }
    static void addContent(NewArticleModel model) {}
}
