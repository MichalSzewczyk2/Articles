package com.articles.articles_library.Interfaces;

import com.articles.articles_library.DTOS.AutorModel;
import com.articles.articles_library.DTOS.NewArticleModel;

import java.util.List;

public interface IAutor {

    static List<AutorModel> getAllAutors() {
        return null;
    }
    static AutorModel getAllAutorById(int id) {
        return null;
    }
    static void addAutor(NewArticleModel model) {}
}
