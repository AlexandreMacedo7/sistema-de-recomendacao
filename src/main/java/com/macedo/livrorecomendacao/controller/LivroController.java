package com.macedo.livrorecomendacao.controller;

import com.macedo.livrorecomendacao.dtos.livrodto.CadastrarLivroDTO;
import com.macedo.livrorecomendacao.service.LivroService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/livro")
public class LivroController {


    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public void cadastrarLivro(@RequestBody CadastrarLivroDTO cadastrarLivroDTO){
        livroService.CadastrarLivro(cadastrarLivroDTO);
    }
}
