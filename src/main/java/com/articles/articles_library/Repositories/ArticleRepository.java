package com.articles.articles_library.Repositories;

import com.articles.articles_library.DTOS.ArticleModel;
import com.articles.articles_library.DTOS.NewArticleModel;
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

    final AuthorRepository autorRepository;
    final ContentRepository contentRepository;

    public ArticleRepository(AuthorRepository autorRepository, ContentRepository contentRepository) {
        this.autorRepository = autorRepository;
        this.contentRepository = contentRepository;
    }


    public List<ArticleModel> getAllArticles() {
        List <Map<String, Object>> articles =  jdbcTemplate.queryForList("SELECT * FROM artykul ORDER BY dataPublikacji DESC");

        List<ArticleModel> APIarticles = new ArrayList<ArticleModel>();
        for (Map article : articles) {




            ArticleModel articleModel = new ArticleModel();
            articleModel.setId((Integer) article.get("id"));
            articleModel.setPublicationDate((Date)article.get("dataPublikacji"));
            articleModel.setArticleName((String)article.get("nazwaCzasopisma"));
            articleModel.setSaveDate((Timestamp) article.get("dataZapisu"));
            articleModel.setAuthor(autorRepository.getAuthorById((Integer)article.get("idAutora")));
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
                    articleModel.setPublicationDate(rs.getDate("dataPublikacji"));
                    articleModel.setArticleName(rs.getString("nazwaCzasopisma"));
                    articleModel.setSaveDate(rs.getTimestamp("dataZapisu"));
                    articleModel.setAuthor(autorRepository.getAuthorById(rs.getInt("idAutora")));
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
            articleModel.setPublicationDate((Date)article.get("dataPublikacji"));
            articleModel.setArticleName((String)article.get("nazwaCzasopisma"));
            articleModel.setSaveDate((Timestamp) article.get("dataZapisu"));
            articleModel.setAuthor(autorRepository.getAuthorById((Integer)article.get("idAutora")));
            articleModel.setContent(contentRepository.getContentById((Integer)article.get("idTresci")));
            APIarticles.add(articleModel);

        }

        return APIarticles;

    }

    public void deleteArticleById(int id) {
        jdbcTemplate.update("DELETE FROM artykul WHERE id=?", id);
    }

    public void newArticle(NewArticleModel model) {
            autorRepository.addAutor(model);
            contentRepository.addContent(model);


        int autorId = autorRepository.addAutor(model);
//            int autorId = jdbcTemplate.queryForObject("SELECT * FROM autor WHERE name = ? AND surname = ?",
//                new Object[]{model.getAuthorName(), model.getAuthorSurname()}, (rs, rowNum) -> {
//                        if (rs.next()) {
//                            return rs.getInt("id");
//                        }
//                        return null;
//                    }
//            );

            System.out.println("autorId = " + autorId);

        int contentId = contentRepository.addContent(model);

//            int contentId = jdbcTemplate.queryForObject("SELECT * FROM tresc WHERE title = ? AND content = ?",
//                    new Object[]{model.getTitle(), model.getContent()}, (rs, rowNum) -> {
//                        if (rs.next()) {
//                            return rs.getInt("id");
//                        }
//                        return null;
//                    }
//            );
            System.out.println("contentId = " + contentId);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            jdbcTemplate.update("INSERT INTO artykul (idTresci,dataPublikacji,nazwaCzasopisma, idAutora,dataZapisu) VALUES (?, ?, ?, ?, ?)",
                    autorId, model.getPublicationDate(), model.getName(), contentId, timestamp);
    }

    public void updateArticle(ArticleModel model){

        jdbcTemplate.update("UPDATE autor SET name = ? , surname = ? WHERE id = ?",
                model.getAuthor().getName(), model.getAuthor().getSurname(),model.getAuthor().getId());

        jdbcTemplate.update("Update tresc SET title = ?, content = ? WHERE id = ?",
                model.getContent().getTitle(), model.getContent().getContent(), model.getContent().getId());

        jdbcTemplate.update("Update artykul SET dataPublikacji = ?, nazwaCzasopisma = ?, dataZapisu = ? WHERE id = ?",
                model.getPublicationDate(), model.getArticleName(), model.getSaveDate(), model.getId());
    }

}


