package com.macedo.livrorecomendacao.controller;

import com.macedo.livrorecomendacao.dtos.avaliacaodto.AvaliacaoDadosGeralDTO;
import com.macedo.livrorecomendacao.dtos.avaliacaodto.AvaliacaoDelecaoDTO;
import com.macedo.livrorecomendacao.dtos.avaliacaodto.CadastrarAvaliacaoDTO;
import com.macedo.livrorecomendacao.service.AvaliacaoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/avaliacao")
public class AvaliacaoController {


    public final AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping
    public ResponseEntity cadastrarAvaliacao(@RequestBody @Valid CadastrarAvaliacaoDTO avaliacaoDTO, UriComponentsBuilder builder) {
        var avaliacao = avaliacaoService.cadastrarAvaliacao(avaliacaoDTO);
        var uri = builder.path("/avaliacao").buildAndExpand(avaliacao).toUri();
        return ResponseEntity.created(uri).body(new CadastrarAvaliacaoDTO(avaliacao));
    }

    @GetMapping
    public ResponseEntity<Page<AvaliacaoDadosGeralDTO>> listarAvaliacoes(@PageableDefault(size = 10, sort = "aluno") Pageable pageable) {
        Page<AvaliacaoDadosGeralDTO> avaliacaoDadosDTOS = avaliacaoService.listarAvaliacoes(pageable);
        return ResponseEntity.ok(avaliacaoDadosDTOS);
    }

    @DeleteMapping
    public ResponseEntity excluirAvaliacao(@RequestBody AvaliacaoDelecaoDTO delecaoDTO) {
        avaliacaoService.deletarAvaliacaoPorMatriculaEIsbn(delecaoDTO);
        return ResponseEntity.noContent().build();
    }
}
