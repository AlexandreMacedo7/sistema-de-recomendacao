package com.macedo.livrorecomendacao.controller;

import com.macedo.livrorecomendacao.dtos.alunodto.AlunoListagemDTO;
import com.macedo.livrorecomendacao.dtos.alunodto.CadastroAlunoDTO;
import com.macedo.livrorecomendacao.dtos.alunodto.DadosAlunoIndividualDTO;
import com.macedo.livrorecomendacao.dtos.alunodto.PesquisaMatriculaDTO;
import com.macedo.livrorecomendacao.service.AlunoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/aluno")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarAluno(@RequestBody @Valid CadastroAlunoDTO dados, UriComponentsBuilder uriBuilder) {
        var aluno = alunoService.cadastraAluno(dados);

        var uri = uriBuilder.path("/aluno/matricula").buildAndExpand(aluno.getMatricula()).toUri();

        return ResponseEntity.created(uri).body(new CadastroAlunoDTO(aluno));
    }

    @PostMapping("/pesquisa")
    public DadosAlunoIndividualDTO dadosAlunoDTO(@RequestBody @Valid PesquisaMatriculaDTO matriculaAluno) {
        DadosAlunoIndividualDTO alunoDTO;
        return alunoDTO = alunoService.listarDadosAluno(matriculaAluno.matricula());
    }

    @GetMapping
    public ResponseEntity<Page<AlunoListagemDTO>> listarAlunos(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        var page = alunoService.listarAlunos(pageable);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity excluirAlunoPorMatricula(@RequestBody PesquisaMatriculaDTO matriculaDTO) {
        alunoService.deletarAluno(matriculaDTO.matricula());
        return ResponseEntity.noContent().build();
    }
}
