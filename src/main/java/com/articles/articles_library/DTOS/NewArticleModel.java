package com.articles.articles_library.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewArticleModel {
    private String title;
    private String content;
    private Date publicationDate;
    private String name;
    private String authorName;
    private String authorSurname;


}
