package com.articles.articles_library.Repositories;

import com.articles.articles_library.DTOS.ArticleModel;
import com.articles.articles_library.DTOS.AutorModel;
import com.articles.articles_library.DTOS.ContentModel;
import com.articles.articles_library.Interfaces.IArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleRepository implements IArticle {

    @Autowired
    JdbcTemplate jdbcTemplate;

    final
    AutorRepository autorRepository;

    public ArticleRepository(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }


    public List<ArticleModel> getAllArticles() {
        List <Map<String, Object>> articles =  jdbcTemplate.queryForList("SELECT * FROM artykul ORDER BY dataPublikacji DESC");

        List<ArticleModel> APIarticles = new ArrayList<ArticleModel>();
        for (Map article : articles) {


            ArticleModel articleModel = new ArticleModel();
            articleModel.setId((Integer) article.get("id"));
            articleModel.setDataPublikacji((Date)article.get("dataPublikacji"));
            articleModel.setNazwaCzasopisma((String)article.get("nazwaCzasopisma"));
            articleModel.setDataZapisu((Timestamp) article.get("dataZapisu"));
            articleModel.setAutor(autorRepository.getAuthorById((Integer)article.get("idAutora")));
            articleModel.setContent(new ContentModel(1,"tytul","tresc"));
            APIarticles.add(articleModel);

        }

        return APIarticles;
    }

    public ArticleModel getArticleById(int id){
       return null;
        // return jdbcTemplate.queryForObject("SELECT * FROM articles WHERE id = ?", new Object[]{})
    }
}
