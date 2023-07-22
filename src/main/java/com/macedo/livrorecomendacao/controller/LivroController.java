package com.macedo.livrorecomendacao.controller;

import com.macedo.livrorecomendacao.dtos.livrodto.CadastrarLivroDTO;
import com.macedo.livrorecomendacao.dtos.livrodto.LivroDTO;
import com.macedo.livrorecomendacao.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/livro")
public class LivroController {


    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public void cadastrarLivro(@RequestBody @Valid CadastrarLivroDTO cadastrarLivroDTO){
        livroService.CadastrarLivro(cadastrarLivroDTO);
    }
    @GetMapping
    public ResponseEntity<Page<LivroDTO>> listarLivros(@PageableDefault(size = 10, sort = "titulo")Pageable pageable){
		Page<LivroDTO>  livroDTOS = livroService.listarLivros(pageable);
        return ResponseEntity.ok(livroDTOS);
    }
}
