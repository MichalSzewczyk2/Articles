package com.articles.articles_library.Repositories;

import com.articles.articles_library.DTOS.AutorModel;
import com.articles.articles_library.DTOS.ContentModel;
import com.articles.articles_library.Interfaces.IContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContentRepository implements IContent {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<ContentModel> getAllContents() {

        return jdbcTemplate.query("SELECT * FROM tresc", BeanPropertyRowMapper.newInstance(ContentModel.class));
    }

}
