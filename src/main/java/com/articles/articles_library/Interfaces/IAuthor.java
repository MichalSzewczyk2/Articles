package com.articles.articles_library.Interfaces;

import com.articles.articles_library.DTOS.AuthorModel;
import com.articles.articles_library.DTOS.NewArticleModel;

import java.util.List;

public interface IAuthor {

    static List<AuthorModel> getAllAutors() {
        return null;
    }
    static AuthorModel getAllAutorById(int id) {
        return null;
    }
    default int addAuthor(NewArticleModel model) {
        return 0;
    }
}
