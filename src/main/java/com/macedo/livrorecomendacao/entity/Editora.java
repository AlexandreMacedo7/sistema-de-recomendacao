package com.macedo.livrorecomendacao.entity;

import com.macedo.livrorecomendacao.dtos.editoradto.CadastrarEditoraDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Editora {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String nome;
    @OneToMany(mappedBy = "editora")
    private List<Livro> livros = new ArrayList<>();

    public Editora(@Valid CadastrarEditoraDTO editoraDTO) {
        this.nome = editoraDTO.nome();
    }
}
