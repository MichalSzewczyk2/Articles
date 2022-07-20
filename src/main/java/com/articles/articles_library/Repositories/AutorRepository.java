package com.articles.articles_library.Repositories;

import com.articles.articles_library.DTOS.AutorModel;
import com.articles.articles_library.DTOS.NewArticleModel;
import com.articles.articles_library.Interfaces.IAutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@Repository
public class AutorRepository implements IAutor
{
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<AutorModel> getAllAutors() {

        return jdbcTemplate.query("SELECT * FROM autor", BeanPropertyRowMapper.newInstance(AutorModel.class));
    }

    public AutorModel getAuthorById(int id) {
        AutorModel autorModel = getAllAutors().stream()
                .filter( t -> id == t.getId())
                .findFirst()
                .orElse(null);
        return autorModel;

    }

    public void addAutor(NewArticleModel model) {

        jdbcTemplate.queryForObject("SELECT * FROM autor WHERE name = ? AND surname = ?",
                new Object[]{model.getAuthorName(), model.getAuthorSurname()}, (rs, rowNum) -> {
                    if(!rs.next()) {
                        jdbcTemplate.update("INSERT INTO autor (name, surname) VALUES (?, ?)",  model.getAuthorName(), model.getAuthorSurname());
                    }
                    return null;
                });
    }
}
