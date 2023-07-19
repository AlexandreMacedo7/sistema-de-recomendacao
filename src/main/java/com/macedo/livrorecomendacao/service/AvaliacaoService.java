package com.macedo.livrorecomendacao.service;

import com.macedo.livrorecomendacao.dtos.avaliacaodto.AvaliacaoDadosDTO;
import com.macedo.livrorecomendacao.dtos.avaliacaodto.CadastrarAvaliacaoDTO;
import com.macedo.livrorecomendacao.entity.Aluno;
import com.macedo.livrorecomendacao.entity.Avaliacao;
import com.macedo.livrorecomendacao.entity.Livro;
import com.macedo.livrorecomendacao.exception.AlunoNaoEncontradoException;
import com.macedo.livrorecomendacao.exception.LivroNaoEncontradoException;
import com.macedo.livrorecomendacao.repository.AlunoRepository;
import com.macedo.livrorecomendacao.repository.AvaliacaoRepository;
import com.macedo.livrorecomendacao.repository.LivroRespository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void cadastrarAvaliacao(CadastrarAvaliacaoDTO avaliacaoDTO){

        Aluno aluno = alunoRepository.findByMatricula(avaliacaoDTO.matricula());
        if (aluno == null){
            throw new AlunoNaoEncontradoException("Aluno não encontrado");
        }
        Livro livro = livroRespository.findByIsbn(avaliacaoDTO.isbn());
        if (livro == null){
            throw new LivroNaoEncontradoException("Livro não encontrado");
        }
        Avaliacao avaliacao = new Avaliacao(aluno, livro, avaliacaoDTO.nota());

        avaliacaoRepository.save(avaliacao);

        livro.getAvaliacaoLista().add(avaliacao);
        aluno.getAvaliacaoLista().add(avaliacao);

        livroRespository.save(livro);
        alunoRepository.save(aluno);
    }

    public Page<AvaliacaoDadosDTO> listarAvaliacoes(Pageable pageable){
        Page<Avaliacao> avaliacoes = avaliacaoRepository.findAll(pageable);
        return avaliacoes.map(avaliacao -> new AvaliacaoDadosDTO(avaliacao.getAluno().getNome(),
                avaliacao.getLivro().getTitulo(), avaliacao.getNota()));
    }
    @Transactional
    public void deletarAvaliacao(Avaliacao avaliacao){
        Livro livro = livroRespository.findByIsbn(avaliacao.getLivro().getIsbn());
        livro.getAvaliacaoLista().remove(avaliacao);
        livroRespository.save(livro);
    }
    @Transactional
    public void deletarAvaliacoesDoAluno(Aluno aluno){
        List<Avaliacao> avaliacoes = aluno.getAvaliacaoLista();
        for (Avaliacao avaliacao : avaliacoes){
            deletarAvaliacao(avaliacao);
        }
    }
}
