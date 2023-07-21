package com.macedo.livrorecomendacao.entity;

import com.macedo.livrorecomendacao.enums.Genero;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"titulo", "autor", "isbn"})})
@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    private String autor;
    @EqualsAndHashCode.Include
    private String isbn;
    private int ano;
    @ManyToOne
    private Editora editora;
    @OneToMany
    private List<Avaliacao> avaliacaoLista = new ArrayList<>();

    public Livro(String titulo, Genero genero, String autor, String isbn, int ano, Editora editora) {
        this.titulo = titulo;
        this.genero = genero;
        this.autor = autor;
        this.isbn = isbn;
        this.ano = ano;
        this.editora = editora;

    }
}
