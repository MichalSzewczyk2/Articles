package com.articles.articles_library.Interfaces;

import com.articles.articles_library.DTOS.AutorModel;
import com.articles.articles_library.DTOS.NewArticleModel;

import java.util.List;

public interface IAuthor {

    static List<AutorModel> getAllAutors() {
        return null;
    }
    static AutorModel getAllAutorById(int id) {
        return null;
    }
    default int addAutor(NewArticleModel model) {
        return 0;
    }
}
