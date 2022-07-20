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

    final AuthorRepository authorRepository;
    final ContentRepository contentRepository;

    public ArticleRepository(AuthorRepository authorRepository, ContentRepository contentRepository) {
        this.authorRepository = authorRepository;
        this.contentRepository = contentRepository;
    }


    public List<ArticleModel> getAllArticles() {
        List <Map<String, Object>> articles =  jdbcTemplate.queryForList("SELECT * FROM article ORDER BY publicationDate DESC");

        List<ArticleModel> APIarticles = new ArrayList<ArticleModel>();
        for (Map article : articles) {




            ArticleModel articleModel = new ArticleModel();
            articleModel.setId((Integer) article.get("id"));
            articleModel.setPublicationDate((Date)article.get("publicationDate"));
            articleModel.setArticleName((String)article.get("articleName"));
            articleModel.setSaveDate((Timestamp) article.get("saveDate"));
            articleModel.setAuthor(authorRepository.getAuthorById((Integer)article.get("idAuthor")));
            articleModel.setContent(contentRepository.getContentById((Integer)article.get("idContent")));
            APIarticles.add(articleModel);

        }

        return APIarticles;
    }

    public ArticleModel getArticleById(int id){

        return jdbcTemplate.queryForObject("SELECT * FROM article WHERE id = ?",
                new Object[] { id }, (rs, rowNum) -> {
                    ArticleModel articleModel = new ArticleModel();
                    articleModel.setId(rs.getInt("id"));
                    articleModel.setPublicationDate(rs.getDate("publicationDate"));
                    articleModel.setArticleName(rs.getString("articleName"));
                    articleModel.setSaveDate(rs.getTimestamp("saveDate"));
                    articleModel.setAuthor(authorRepository.getAuthorById(rs.getInt("idAuthor")));
                    articleModel.setContent(contentRepository.getContentById(rs.getInt("idContent")));
                    return articleModel;}
        );
    }

    public List<ArticleModel> getArticleByWord(String word){



        List <Map<String, Object>> articles = jdbcTemplate.queryForList("SELECT * FROM article NATURAL JOIN content WHERE title LIKE ? OR content LIKE ?",
                new Object[]{"%" + word + "%", "%" + word + "%"});


        List<ArticleModel> APIarticles = new ArrayList<ArticleModel>();
        for (Map article : articles) {

            ArticleModel articleModel = new ArticleModel();
            articleModel.setId((Integer) article.get("id"));
            articleModel.setPublicationDate((Date)article.get("publicationDate"));
            articleModel.setArticleName((String)article.get("articleName"));
            articleModel.setSaveDate((Timestamp) article.get("saveDate"));
            articleModel.setAuthor(authorRepository.getAuthorById((Integer)article.get("idAuthor")));
            articleModel.setContent(contentRepository.getContentById((Integer)article.get("idContent")));
            APIarticles.add(articleModel);

        }

        return APIarticles;

    }

    public void deleteArticleById(int id) {

        if(id <=0){throw new IllegalArgumentException("id must be greater than 0");}

        jdbcTemplate.update("DELETE FROM article WHERE id=?", id);
    }

    public void newArticle(NewArticleModel model) {
            authorRepository.addAuthor(model);
            contentRepository.addContent(model);


        int authorId = authorRepository.addAuthor(model);


        int contentId = contentRepository.addContent(model);


            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            jdbcTemplate.update("INSERT INTO article (idContent, publicationDate, articleName, idAuthor, saveDate) VALUES (?, ?, ?, ?, ?)",
                    contentId, model.getPublicationDate(), model.getName(), authorId, timestamp);
    }

    public void updateArticle(ArticleModel model){

        jdbcTemplate.update("UPDATE author SET name = ?, surname = ? WHERE id = ?",
                model.getAuthor().getName(), model.getAuthor().getSurname(),model.getAuthor().getId());

        jdbcTemplate.update("Update content SET title = ?, content = ? WHERE id = ?",
                model.getContent().getTitle(), model.getContent().getContent(), model.getContent().getId());

        jdbcTemplate.update("Update article SET publicationDate = ?, articleName = ?, saveDate = ? WHERE id = ?",
                model.getPublicationDate(), model.getArticleName(), model.getSaveDate(), model.getId());
    }

}


