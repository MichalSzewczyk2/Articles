package com.articles.articles_library.Controllers;

import com.articles.articles_library.DTOS.AutorModel;
import com.articles.articles_library.Repositories.AuthorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    final
    AuthorRepository autorRepository;

    public AuthorController(AuthorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @GetMapping("/allAuthors")
    public List<AutorModel> getAllAutorsEnd(){
       return autorRepository.getAllAutors();
    }

    @RequestMapping(value = "/Author/{id}", method = RequestMethod.GET)
    public AutorModel getAuthor(@PathVariable int id){
        return autorRepository.getAuthorById(id);
    }
}
