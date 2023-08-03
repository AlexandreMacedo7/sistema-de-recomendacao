package com.macedo.livrorecomendacao.dtos.livrodto;

import com.macedo.livrorecomendacao.dtos.editoradto.CadastrarEditoraDTO;
import com.macedo.livrorecomendacao.entity.Livro;
import com.macedo.livrorecomendacao.enums.Genero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarLivroDTO(

        @NotBlank
        String titulo,
        @NotNull
        Genero genero,
        @NotBlank
        String autor,
        @NotBlank
        String isbn,
        @NotNull
        int ano,
        @NotNull
        CadastrarEditoraDTO editora
) {
    public CadastrarLivroDTO(Livro livro) {
        this(livro.getTitulo(), livro.getGenero(), livro.getAutor(), livro.getIsbn(), livro.getAno(),new CadastrarEditoraDTO(livro.getEditora().getNome()));
    }
}
