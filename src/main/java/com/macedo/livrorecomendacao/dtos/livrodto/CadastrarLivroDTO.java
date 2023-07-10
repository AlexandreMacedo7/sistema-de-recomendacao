package com.macedo.livrorecomendacao.dtos.livrodto;

import com.macedo.livrorecomendacao.dtos.editoradto.CadastrarEditoraDTO;
import com.macedo.livrorecomendacao.enums.Genero;

public record CadastrarLivroDTO(
    String nome,
    Genero genero,
    String autor,
    String isbn,
    int ano,
    CadastrarEditoraDTO editora
) {
}
