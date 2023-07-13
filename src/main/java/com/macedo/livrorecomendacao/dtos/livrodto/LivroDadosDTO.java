package com.macedo.livrorecomendacao.dtos.livrodto;

import com.macedo.livrorecomendacao.entity.Avaliacao;
import com.macedo.livrorecomendacao.enums.Genero;

import java.util.List;

public record LivroDadosDTO(
        String titulo,
        String autor,
        Genero genero,
        String isbn,
        List<Avaliacao> listaAvaliacoes
){
    public LivroDadosDTO(String titulo, String autor, Genero genero, String isbn, List<Avaliacao> listaAvaliacoes) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.isbn = isbn;
        this.listaAvaliacoes = listaAvaliacoes;
    }
}
