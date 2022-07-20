package com.articles.articles_library.Repositories;

import com.articles.articles_library.DTOS.ArticleModel;
import com.articles.articles_library.DTOS.ContentModel;
import com.articles.articles_library.Interfaces.IArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

    final AutorRepository autorRepository;
    final ContentRepository contentRepository;

    public ArticleRepository(AutorRepository autorRepository, ContentRepository contentRepository) {
        this.autorRepository = autorRepository;
        this.contentRepository = contentRepository;
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
            articleModel.setContent(contentRepository.getContentById((Integer)article.get("idTresci")));
            APIarticles.add(articleModel);

        }

        return APIarticles;
    }

    public ArticleModel getArticleById(int id){

        return jdbcTemplate.queryForObject("SELECT * FROM artykul WHERE id = ?",
                new Object[] { id }, (rs, rowNum) -> {
                    ArticleModel articleModel = new ArticleModel();
                    articleModel.setId(rs.getInt("id"));
                    articleModel.setDataPublikacji(rs.getDate("dataPublikacji"));
                    articleModel.setNazwaCzasopisma(rs.getString("nazwaCzasopisma"));
                    articleModel.setDataZapisu(rs.getTimestamp("dataZapisu"));
                    articleModel.setAutor(autorRepository.getAuthorById(rs.getInt("idAutora")));
                    articleModel.setContent(contentRepository.getContentById(rs.getInt("idTresci")));
                    return articleModel;}
        );
    }

    public List<ArticleModel> getArticleByWord(String word){



        List <Map<String, Object>> articles = jdbcTemplate.queryForList("SELECT * FROM artykul NATURAL JOIN tresc t WHERE t.title LIKE ? OR t.content LIKE ?",
                new Object[] { "%" + word + "%", "%" + word + "%"});

        List<ArticleModel> APIarticles = new ArrayList<ArticleModel>();
        for (Map article : articles) {

            ArticleModel articleModel = new ArticleModel();
            articleModel.setId((Integer) article.get("id"));
            articleModel.setDataPublikacji((Date)article.get("dataPublikacji"));
            articleModel.setNazwaCzasopisma((String)article.get("nazwaCzasopisma"));
            articleModel.setDataZapisu((Timestamp) article.get("dataZapisu"));
            articleModel.setAutor(autorRepository.getAuthorById((Integer)article.get("idAutora")));
            articleModel.setContent(contentRepository.getContentById((Integer)article.get("idTresci")));
            APIarticles.add(articleModel);

        }

        return APIarticles;

    }
}
