package com.macedo.livrorecomendacao.dtos.livrodto;

import com.macedo.livrorecomendacao.dtos.editoradto.CadastrarEditoraDTO;
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
        @NotBlank
        int ano,
        @NotNull
        CadastrarEditoraDTO editora
) {
}
