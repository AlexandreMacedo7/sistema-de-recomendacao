package com.macedo.livrorecomendacao.entity;

import com.macedo.livrorecomendacao.dtos.editoradto.CadastrarEditoraDTO;
import com.macedo.livrorecomendacao.enums.Genero;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    private String autor;
    private String isbn;
    private int ano;
    @ManyToOne
    private Editora editora;
    @OneToMany
    private List<Avaliacao> avaliacaoLista = new ArrayList<>();

    public Livro(String titulo, String autor, String isbn, int ano, Editora editora) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.ano = ano;
        this.editora = editora;

    }
}
