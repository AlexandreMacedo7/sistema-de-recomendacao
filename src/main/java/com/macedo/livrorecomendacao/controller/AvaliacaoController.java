package com.macedo.livrorecomendacao.controller;

import com.macedo.livrorecomendacao.dtos.avaliacaodto.AvaliacaoDadosGeralDTO;
import com.macedo.livrorecomendacao.dtos.avaliacaodto.CadastrarAvaliacaoDTO;
import com.macedo.livrorecomendacao.service.AvaliacaoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping
    public ResponseEntity<Page<AvaliacaoDadosGeralDTO>> listarAvaliacoes(@PageableDefault(size = 10, sort = "aluno")Pageable pageable){
        Page<AvaliacaoDadosGeralDTO> avaliacaoDadosDTOS = avaliacaoService.listarAvaliacoes(pageable);
        return ResponseEntity.ok(avaliacaoDadosDTOS);
    }
}
