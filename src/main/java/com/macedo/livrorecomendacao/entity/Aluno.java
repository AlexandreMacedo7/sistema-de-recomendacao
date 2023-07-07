package com.macedo.livrorecomendacao.entity;


import com.macedo.livrorecomendacao.enums.Turno;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Aluno {

    private Long id;
    private String nome;
    private String matricula;
    private String turma;

    @Enumerated(EnumType.STRING)
    private Turno turno;
}

