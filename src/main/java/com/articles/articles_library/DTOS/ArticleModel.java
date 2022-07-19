package com.articles.articles_library.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleModel {
    private int id;
    private ContentModel content;
    private Date dataPublikacji;
    private String nazwaCzasopisma;
    private AutorModel autor;
    private Timestamp dataZapisu;

}
