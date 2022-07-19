package com.articles.articles_library.Controllers;

import com.articles.articles_library.DTOS.ContentModel;
import com.articles.articles_library.Repositories.ContentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContentController {

    final
    ContentRepository contentRepository;

    public ContentController(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @GetMapping("/allContents")
    public List<ContentModel> getAllContentsEnd(){
        return contentRepository.getAllContents();
    }

}
