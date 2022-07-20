package com.articles.articles_library.Repositories;

import com.articles.articles_library.DTOS.ContentModel;
import com.articles.articles_library.DTOS.NewArticleModel;
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

        return jdbcTemplate.query("SELECT * FROM content", BeanPropertyRowMapper.newInstance(ContentModel.class));
    }

    public ContentModel getContentById(int id) {
        ContentModel contentModel = getAllContents().stream()
                .filter( t -> id == t.getId())
                .findFirst()
                .orElse(null);
        return contentModel;
    }

    public int addContent(NewArticleModel model) {


        List<ContentModel> contentModels = getAllContents();

        int tmpId = 0;

        for (ContentModel contentModel : contentModels) {
            if (contentModel.getTitle().equals(model.getTitle()) && contentModel.getContent().equals(model.getContent())) {
                tmpId = contentModel.getId();
                return tmpId;
            }
        }
        jdbcTemplate.update("INSERT INTO content (title, content) VALUES (?, ?)", model.getTitle(), model.getContent());
        return tmpId + 1;
    }
}
