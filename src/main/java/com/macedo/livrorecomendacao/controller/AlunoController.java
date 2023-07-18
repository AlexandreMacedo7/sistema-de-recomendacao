package com.macedo.livrorecomendacao.controller;

import com.macedo.livrorecomendacao.dtos.alunodto.AlunoDTO;
import com.macedo.livrorecomendacao.dtos.alunodto.CadastroAlunoDTO;
import com.macedo.livrorecomendacao.dtos.alunodto.DadosAlunoDTO;
import com.macedo.livrorecomendacao.dtos.alunodto.PesquisaMatriculaDTO;
import com.macedo.livrorecomendacao.service.AlunoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/aluno")
public class AlunoController {

    private final AlunoService alunoService;
    public AlunoController(AlunoService alunoService){
        this.alunoService = alunoService;
    }

    @PostMapping
    public void cadastrarAluno(@RequestBody CadastroAlunoDTO dados){
        alunoService.cadastraAluno(dados);
    }

    @PostMapping("/pesquisa")
    public DadosAlunoDTO dadosAlunoDTO(@RequestBody PesquisaMatriculaDTO matriculaAluno){
        DadosAlunoDTO alunoDTO;
        return alunoDTO = alunoService.listarDadosAluno(matriculaAluno.matricula());
    }
    @GetMapping
    public List<AlunoDTO> listarAlunos(){
        return alunoService.listarAlunos();
    }
}
