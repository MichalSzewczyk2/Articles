package com.articles.articles_library.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutorModel {
    private int id;
    private String name;
    private String surname;
}
