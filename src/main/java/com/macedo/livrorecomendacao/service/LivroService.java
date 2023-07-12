package com.macedo.livrorecomendacao.service;

import com.macedo.livrorecomendacao.dtos.editoradto.CadastrarEditoraDTO;
import com.macedo.livrorecomendacao.dtos.livrodto.CadastrarLivroDTO;
import com.macedo.livrorecomendacao.entity.Editora;
import com.macedo.livrorecomendacao.entity.Livro;
import com.macedo.livrorecomendacao.repository.LivroRespository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class LivroService {


    private final LivroRespository livroRespository;
    private final EditoraService editoraService;

    public LivroService(LivroRespository livroRespository, EditoraService editoraService){
        this.livroRespository = livroRespository;
        this.editoraService = editoraService;
    }

    public void CadastrarLivro(@Valid CadastrarLivroDTO cadastrarLivroDTO){

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
        livroRespository.save(livro);
    }
}
