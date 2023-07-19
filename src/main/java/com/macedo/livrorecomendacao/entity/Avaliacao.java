package com.macedo.livrorecomendacao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matricula_aluno", referencedColumnName = "matricula")
    @NotNull
    private Aluno aluno;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "isbn_livro", referencedColumnName = "isbn")
    @NotNull
    private Livro livro;
    @NotNull
    private double nota;

    public Avaliacao(Aluno aluno, Livro livro, double nota) {
        this.aluno = aluno;
        this.livro = livro;
        this.nota = nota;
    }
}
