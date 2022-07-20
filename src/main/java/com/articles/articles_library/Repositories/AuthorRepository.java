package com.articles.articles_library.Repositories;

import com.articles.articles_library.DTOS.AutorModel;
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

    public int addAutor(NewArticleModel model) {

        List<AutorModel> autorModels = getAllAutors();

        int tmpId = 0;

        for (AutorModel autorModel : autorModels) {
            if (autorModel.getName().equals(model.getAuthorName()) && autorModel.getSurname().equals(model.getAuthorSurname())) {
                tmpId = autorModel.getId();
                return tmpId;
            }
        }
        System.out.println("tmpId" + tmpId);
        jdbcTemplate.update("INSERT INTO autor (name, surname) VALUES (?, ?)",  model.getAuthorName(), model.getAuthorSurname());
        return tmpId + 1;


//        AutorModel autorModel = jdbcTemplate.queryForObject("SELECT * FROM autor WHERE name = ? AND surname = ?",
//                new Object[]{model.getAuthorName(), model.getAuthorSurname()}, (rs, rowNum) -> {
//                    if(rs.next()){
//                        return new AutorModel(rs.getInt("id"), rs.getString("name"),rs.getString("surname"));
//                    }
//                    return null;
//                }
//                );
//
//        if(autorModel == null){
//            jdbcTemplate.update("INSERT INTO autor (name, surname) VALUES (?, ?)",  model.getAuthorName(), model.getAuthorSurname());
//        }
    }
}
