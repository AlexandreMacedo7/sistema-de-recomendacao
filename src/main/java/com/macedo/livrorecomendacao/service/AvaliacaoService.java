package com.macedo.livrorecomendacao.service;

import com.macedo.livrorecomendacao.dtos.avaliacaodto.AvaliacaoDadosGeralDTO;
import com.macedo.livrorecomendacao.dtos.avaliacaodto.AvaliacaoDelecaoDTO;
import com.macedo.livrorecomendacao.dtos.avaliacaodto.CadastrarAvaliacaoDTO;
import com.macedo.livrorecomendacao.entity.Aluno;
import com.macedo.livrorecomendacao.entity.Avaliacao;
import com.macedo.livrorecomendacao.entity.Livro;
import com.macedo.livrorecomendacao.exception.AlunoNaoEncontradoException;
import com.macedo.livrorecomendacao.exception.LivroNaoEncontradoException;
import com.macedo.livrorecomendacao.exception.NotaInvalidaException;
import com.macedo.livrorecomendacao.repository.AlunoRepository;
import com.macedo.livrorecomendacao.repository.AvaliacaoRepository;
import com.macedo.livrorecomendacao.repository.LivroRespository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoService {

    private final AlunoRepository alunoRepository;
    private final LivroRespository livroRespository;
    private final AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoService(AlunoRepository alunoRepository, LivroRespository livroRespository, AvaliacaoRepository avaliacaoRepository) {
        this.alunoRepository = alunoRepository;
        this.livroRespository = livroRespository;
        this.avaliacaoRepository = avaliacaoRepository;
    }

    @Transactional
    public void cadastrarAvaliacao(@Valid CadastrarAvaliacaoDTO avaliacaoDTO) {

        Aluno aluno = alunoRepository.findByMatricula(avaliacaoDTO.matricula());
        if (aluno == null) {
            throw new AlunoNaoEncontradoException("Aluno não encontrado");
        }
        Livro livro = livroRespository.findByIsbn(avaliacaoDTO.isbn());
        if (livro == null) {
            throw new LivroNaoEncontradoException("Livro não encontrado");
        }
        validacaoDeNota(avaliacaoDTO.nota());
        Avaliacao avaliacao = new Avaliacao(aluno, livro, avaliacaoDTO.nota());

        avaliacaoRepository.save(avaliacao);

        livro.getAvaliacaoLista().add(avaliacao);
        aluno.getAvaliacaoLista().add(avaliacao);

        livroRespository.save(livro);
        alunoRepository.save(aluno);
    }

    public Page<AvaliacaoDadosGeralDTO> listarAvaliacoes(Pageable pageable) {
        Page<Avaliacao> avaliacoes = avaliacaoRepository.findAll(pageable);
        return avaliacoes.map(avaliacao -> new AvaliacaoDadosGeralDTO(
                avaliacao.getAluno().getNome(), avaliacao.getAluno().getMatricula(),
                avaliacao.getLivro().getTitulo(), avaliacao.getLivro().getIsbn(), avaliacao.getNota()));
    }

    @Transactional
    public void deletarAvaliacoesDoLivro(Aluno aluno) {
        List<Avaliacao> avaliacoes = aluno.getAvaliacaoLista();
        for (Avaliacao avaliacao : avaliacoes) {
            Livro livro = livroRespository.findByIsbn(avaliacao.getLivro().getIsbn());
            livro.getAvaliacaoLista().remove(avaliacao);
            livroRespository.save(livro);
        }
    }

    @Transactional
    public void deletarAvaliacaoPorMatriculaEIsbn(AvaliacaoDelecaoDTO avaliacaoDelecaoDTO) {
        try {
            Aluno aluno = alunoRepository.findByMatricula(avaliacaoDelecaoDTO.matricula());
            if (aluno == null) {
                throw new AlunoNaoEncontradoException("Aluno não encontrado para a matrícula " + avaliacaoDelecaoDTO.matricula());
            }

            Livro livro = livroRespository.findByIsbn(avaliacaoDelecaoDTO.isbn());
            if (livro == null) {
                throw new LivroNaoEncontradoException("Livro não encontrado para o ISBN " + avaliacaoDelecaoDTO.isbn());
            }
            Optional<Avaliacao> avaliacaoOptional = aluno.getAvaliacaoLista().stream()
                    .filter(avaliacao -> avaliacao.getLivro().getIsbn()
                            .equals(livro.getIsbn())).findFirst();
            if (avaliacaoOptional.isPresent()) {
                Avaliacao avaliacao = avaliacaoOptional.get();
                deletarAvaliacao(aluno, avaliacao);
            }
        } catch (AlunoNaoEncontradoException | LivroNaoEncontradoException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("Erro na exclusão da avaliação - ", ex);
        }
    }
	@Transactional
    private void deletarAvaliacao(Aluno aluno, Avaliacao avaliacao) {
        deletarAvaliacoesDoLivro(aluno);
        aluno.getAvaliacaoLista().remove(avaliacao);
        alunoRepository.save(aluno);
        avaliacaoRepository.delete(avaliacao);
    }

    private void validacaoDeNota(double nota) {
        if (nota < 1 || nota > 5)
            throw new NotaInvalidaException("A nota deve estar entre 1 e 5");
    }
}
