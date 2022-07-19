package com.articles.articles_library.Repositories;

import com.articles.articles_library.DTOS.AutorModel;
import com.articles.articles_library.Interfaces.IAutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AutorRepository implements IAutor
{
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<AutorModel> getAllAutors() {

        return jdbcTemplate.query("SELECT * FROM autor", BeanPropertyRowMapper.newInstance(AutorModel.class));
    }

}
