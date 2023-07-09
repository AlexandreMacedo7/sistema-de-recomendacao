package com.macedo.livrorecomendacao.service;

import com.macedo.livrorecomendacao.dtos.CadastroAlunoDTO;
import com.macedo.livrorecomendacao.entity.Aluno;
import com.macedo.livrorecomendacao.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository){
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public void cadastraAluno(CadastroAlunoDTO cadastroAlunoDTO){
        Aluno aluno = new Aluno(
                cadastroAlunoDTO.nome(),
                cadastroAlunoDTO.matricula(),
                cadastroAlunoDTO.email(),
                cadastroAlunoDTO.telefne(),
                cadastroAlunoDTO.turma(),
                cadastroAlunoDTO.turno()
        );

        alunoRepository.save(aluno);
    }

}