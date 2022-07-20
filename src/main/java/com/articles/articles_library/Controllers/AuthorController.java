package com.articles.articles_library.Controllers;

import com.articles.articles_library.DTOS.AuthorModel;
import com.articles.articles_library.Repositories.AuthorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    final
    AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/allAuthors")
    public List<AuthorModel> getAllAuthorsEnd(){
       return authorRepository.getAllAuthors();
    }

    @RequestMapping(value = "/Author/{id}", method = RequestMethod.GET)
    public AuthorModel getAuthor(@PathVariable int id){
        return authorRepository.getAuthorById(id);
    }
}
