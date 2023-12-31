package com.macedo.livrorecomendacao.service;

import com.macedo.livrorecomendacao.dtos.alunodto.AlunoAtualizacaoDTO;
import com.macedo.livrorecomendacao.dtos.alunodto.AlunoListagemDTO;
import com.macedo.livrorecomendacao.dtos.alunodto.CadastroAlunoDTO;
import com.macedo.livrorecomendacao.dtos.alunodto.DadosAlunoIndividualDTO;
import com.macedo.livrorecomendacao.dtos.avaliacaodto.AvaliacaoDadosDTO;
import com.macedo.livrorecomendacao.entity.Aluno;
import com.macedo.livrorecomendacao.exception.AlunoNaoEncontradoException;
import com.macedo.livrorecomendacao.mapper.AlunoMapper;
import com.macedo.livrorecomendacao.repository.AlunoRepository;
import com.macedo.livrorecomendacao.repository.AvaliacaoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final AvaliacaoRepository avaliacaoRepository;
    private final AvaliacaoService avaliacaoService;

    public AlunoService(AlunoRepository alunoRepository,
                        AvaliacaoRepository avaliacaoRepository,
                        AvaliacaoService avaliacaoService) {
        this.alunoRepository = alunoRepository;
        this.avaliacaoRepository = avaliacaoRepository;
        this.avaliacaoService = avaliacaoService;
    }

    @Transactional
    public Aluno cadastraAluno(CadastroAlunoDTO cadastroAlunoDTO) {
        Aluno aluno = new Aluno(
                cadastroAlunoDTO.nome(),
                cadastroAlunoDTO.matricula(),
                cadastroAlunoDTO.email(),
                cadastroAlunoDTO.telefone(),
                cadastroAlunoDTO.turma(),
                cadastroAlunoDTO.turno()
        );

        return alunoRepository.save(aluno);
    }

    @Transactional
    public void atualizarAluno(@Valid AlunoAtualizacaoDTO dadosDTO) {
        var aluno = alunoRepository.findByMatricula(dadosDTO.matricula());
        if (aluno != null) {
            AlunoMapper.INSTANCE.updateAlunoFromDto(dadosDTO, aluno);
        }else {
            throw new AlunoNaoEncontradoException("Matricula" + dadosDTO.matricula()
                    + "não corresponde a nenhum aluno");
        }
    }

    public DadosAlunoIndividualDTO listarDadosAluno(String matricula) {

        try {
            Aluno aluno = alunoRepository.findByMatricula(matricula);

            if (aluno == null) {
                throw new AlunoNaoEncontradoException("Matricula: " + matricula + "Não encontrada!");
            }
            List<AvaliacaoDadosDTO> avaliacaoDadosDTOS = aluno.getAvaliacaoLista().stream().map(avaliacao -> new AvaliacaoDadosDTO(
                    avaliacao.getLivro().getTitulo(), avaliacao.getLivro().getIsbn(), avaliacao.getNota())).collect(Collectors.toList());
            return new DadosAlunoIndividualDTO(aluno.getNome(), aluno.getEmail(),
                    aluno.getTurma(), aluno.getTurno(), avaliacaoDadosDTOS);
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao buscar dados do aluno " + ex.getMessage());
        }
    }

    public Page<AlunoListagemDTO> listarAlunos(Pageable pageable) {
        Page<Aluno> alunos = alunoRepository.findAll(pageable);
        return alunos.map(aluno -> new AlunoListagemDTO(aluno.getMatricula(), aluno.getNome(), aluno.getTurma(),
                aluno.getTurno(), aluno.getEmail(), aluno.getTelefone()));
    }

    @Transactional
    public void deletarAluno(String matricula) {

        try {
            Optional<Aluno> optionalAluno = Optional.ofNullable(alunoRepository.findByMatricula(matricula));
            if (optionalAluno.isEmpty()) {
                throw new AlunoNaoEncontradoException("Matricula não encontrada");
            }
            Aluno aluno = optionalAluno.get();
            avaliacaoService.deletarAvaliacoesDoLivro(aluno);
            alunoRepository.delete(aluno);
        } catch (NullPointerException ex) {
            throw new IllegalArgumentException("A matricula não pode ser nula - ", ex);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Erro ao excluir aluno no Banco de dados - ", ex);
        } catch (TransactionSystemException ex) {
            throw new RuntimeException("Erro ao executar transação - ", ex);
        }
    }
}
