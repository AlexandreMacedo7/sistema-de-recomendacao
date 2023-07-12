package com.macedo.livrorecomendacao.controller;

import com.macedo.livrorecomendacao.dtos.avaliacao.CadastrarAvaliacaoDTO;
import com.macedo.livrorecomendacao.service.AvaliacaoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/avaliacao")
public class AvaliacaoController {


    public final AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping
    public void cadastrarAvaliacao(@RequestBody @Valid CadastrarAvaliacaoDTO avaliacaoDTO){
        avaliacaoService.cadastrarAvaliacao(avaliacaoDTO);
    }
}
