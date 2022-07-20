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
    private Date publicationDate;
    private String articleName;
    private AutorModel author;
    private Timestamp saveDate;

}
