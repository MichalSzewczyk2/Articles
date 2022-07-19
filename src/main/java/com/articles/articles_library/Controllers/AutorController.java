package com.articles.articles_library.Controllers;

import com.articles.articles_library.DTOS.AutorModel;
import com.articles.articles_library.Interfaces.IAutor;
import com.articles.articles_library.Repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
