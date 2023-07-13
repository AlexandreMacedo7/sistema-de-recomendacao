package com.macedo.livrorecomendacao.dtos.alunodto;

import com.macedo.livrorecomendacao.dtos.avaliacaodto.AvaliacaoDadosDTO;
import com.macedo.livrorecomendacao.enums.Turno;

import java.util.List;

public record DadosAlunoDTO(
        String nome,
        String email,
        String turma,
        Turno turno,
        List<AvaliacaoDadosDTO> avaliacoesFeitas
) {
    public DadosAlunoDTO(String nome, String email, String turma, Turno turno, List<AvaliacaoDadosDTO> avaliacoesFeitas) {
        this.nome = nome;
        this.email = email;
        this.turma = turma;
        this.turno = turno;
        this.avaliacoesFeitas = avaliacoesFeitas;
    }
}
