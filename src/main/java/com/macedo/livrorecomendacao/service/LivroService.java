package com.macedo.livrorecomendacao.service;

import com.macedo.livrorecomendacao.dtos.editoradto.CadastrarEditoraDTO;
import com.macedo.livrorecomendacao.dtos.editoradto.EditoraDTO;
import com.macedo.livrorecomendacao.dtos.livrodto.CadastrarLivroDTO;
import com.macedo.livrorecomendacao.dtos.livrodto.LivroDTO;
import com.macedo.livrorecomendacao.entity.Editora;
import com.macedo.livrorecomendacao.entity.Livro;
import com.macedo.livrorecomendacao.repository.LivroRespository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LivroService {


    private final LivroRespository livroRespository;
    private final EditoraService editoraService;

    public LivroService(LivroRespository livroRespository, EditoraService editoraService){
        this.livroRespository = livroRespository;
        this.editoraService = editoraService;
    }

    @Transactional
    public Livro CadastrarLivro(@Valid CadastrarLivroDTO cadastrarLivroDTO){

        CadastrarEditoraDTO editoraDTO = cadastrarLivroDTO.editora();

        Editora editora = editoraService.criarEditora(editoraDTO);

        Livro livro = new Livro(
                cadastrarLivroDTO.titulo(),
                cadastrarLivroDTO.genero(),
                cadastrarLivroDTO.autor(),
                cadastrarLivroDTO.isbn(),
                cadastrarLivroDTO.ano(),
                editora
        );
        return livroRespository.save(livro);
    }
    public Page<LivroDTO> listarLivros(Pageable pageable){
        Page<Livro> livros = livroRespository.findAll(pageable);
        return livros.map(livro -> new LivroDTO( livro.getIsbn(),
                livro.getTitulo(),livro.getGenero(), livro.getAutor(), livro.getAno(), new EditoraDTO(livro.getEditora().getNome())));
    }
}
