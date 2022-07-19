package com.articles.articles_library.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentModel {
    private int id;
    private String title;
    private String content;
}
