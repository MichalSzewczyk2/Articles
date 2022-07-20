package com.articles.articles_library.Repositories;

import com.articles.articles_library.DTOS.AuthorModel;
import com.articles.articles_library.DTOS.NewArticleModel;
import com.articles.articles_library.Interfaces.IAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorRepository implements IAuthor
{
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<AuthorModel> getAllAuthors() {
        return jdbcTemplate.query("SELECT * FROM author", BeanPropertyRowMapper.newInstance(AuthorModel.class));
    }

    public AuthorModel getAuthorById(int id) {

        if(id <= 0){
            throw new IllegalArgumentException("Id must be greater than 0");
        }

        AuthorModel authorModel = getAllAuthors().stream()
                .filter( t -> id == t.getId())
                .findFirst()
                .orElse(null);
        return authorModel;

    }

    public int addAuthor(NewArticleModel model) {

        List<AuthorModel> authorModels = getAllAuthors();

        int tmpId = 0;

        for (AuthorModel authorModel : authorModels) {
            if (authorModel.getName().equals(model.getAuthorName()) && authorModel.getSurname().equals(model.getAuthorSurname())) {
                tmpId = authorModel.getId();
                return tmpId;
            }
        }
        jdbcTemplate.update("INSERT INTO author (name, surname) VALUES (?, ?)",  model.getAuthorName(), model.getAuthorSurname());
        return tmpId + 1;

    }
}
