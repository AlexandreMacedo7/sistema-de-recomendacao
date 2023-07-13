package com.macedo.livrorecomendacao.service;

import com.macedo.livrorecomendacao.dtos.alunodto.CadastroAlunoDTO;
import com.macedo.livrorecomendacao.dtos.alunodto.DadosAlunoDTO;
import com.macedo.livrorecomendacao.dtos.avaliacaodto.AvaliacaoDadosDTO;
import com.macedo.livrorecomendacao.entity.Aluno;
import com.macedo.livrorecomendacao.exception.AlunoNaoEncontradoException;
import com.macedo.livrorecomendacao.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public void cadastraAluno(CadastroAlunoDTO cadastroAlunoDTO) {
        Aluno aluno = new Aluno(
                cadastroAlunoDTO.nome(),
                cadastroAlunoDTO.matricula(),
                cadastroAlunoDTO.email(),
                cadastroAlunoDTO.telefone(),
                cadastroAlunoDTO.turma(),
                cadastroAlunoDTO.turno()
        );

        alunoRepository.save(aluno);
    }

    public DadosAlunoDTO listarDadosAluno(String matricula) {

        try {
            Aluno aluno = alunoRepository.findByMatricula(matricula);

            if (aluno == null) {
                throw new AlunoNaoEncontradoException("Matricula: " + matricula + "Não encontrada!");
            }
            List<AvaliacaoDadosDTO> avaliacaoDadosDTOS = aluno.getAvaliacaoLista().stream().map(avaliacao -> new AvaliacaoDadosDTO(
                    avaliacao.getLivro().getTitulo(), avaliacao.getLivro().getIsbn(), avaliacao.getNota())).collect(Collectors.toList());
            return new DadosAlunoDTO(aluno.getNome(), aluno.getEmail(),
                    aluno.getTurma(), aluno.getTurno(), avaliacaoDadosDTOS);
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao buscar dados do aluno " + ex.getMessage());
        }
    }
}
