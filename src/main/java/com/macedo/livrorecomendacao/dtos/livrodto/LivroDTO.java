package com.macedo.livrorecomendacao.dtos.livrodto;

import com.macedo.livrorecomendacao.dtos.editoradto.EditoraDTO;
import com.macedo.livrorecomendacao.entity.Livro;
import com.macedo.livrorecomendacao.enums.Genero;

public record LivroDTO(String isbn, String titulo, Genero genero, String autor, int ano, EditoraDTO editora) {
}
