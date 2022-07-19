package com.articles.articles_library.Controllers;

import com.articles.articles_library.DTOS.AutorModel;
import com.articles.articles_library.Interfaces.IAutor;
import com.articles.articles_library.Repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AutorController {

    final
    AutorRepository autorRepository;

    public AutorController(AutorRepository autorRepository) {
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
