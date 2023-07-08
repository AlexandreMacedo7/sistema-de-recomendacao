package com.macedo.livrorecomendacao.entity;


import com.macedo.livrorecomendacao.enums.Turno;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Nome de aluno não pode ser vazio.")
    private String nome;
    @NotBlank(message = "Matricula não pode ser vazia.")
    private String matricula;
    @NotBlank(message = "Email não pode ser vazio")
    @Email
    private String email;
    @NotBlank(message = "Telefone não pode ser vazio")
    private String telefone;
    @NotBlank(message = "Turma não pode ser vazia.")
    private String turma;
    @Enumerated(EnumType.STRING)
    private Turno turno;
    @OneToMany
    private List<Avaliacao> avaliacaoLista = new ArrayList<>();
}

