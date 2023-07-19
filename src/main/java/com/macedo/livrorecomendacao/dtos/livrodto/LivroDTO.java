package com.macedo.livrorecomendacao.dtos.livrodto;

import com.macedo.livrorecomendacao.dtos.editoradto.EditoraDTO;
import com.macedo.livrorecomendacao.entity.Livro;
import com.macedo.livrorecomendacao.enums.Genero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroDTO(
        @NotBlank
        String isbn,
        @NotBlank
        String titulo,
        @NotNull
        Genero genero,
        @NotBlank
        String autor,
        @NotBlank
        int ano,
        @NotNull
        EditoraDTO editora) {
}
