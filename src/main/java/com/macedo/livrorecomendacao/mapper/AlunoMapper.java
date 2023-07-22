package com.macedo.livrorecomendacao.mapper;

import com.macedo.livrorecomendacao.dtos.alunodto.AlunoAtualizacaoDTO;
import com.macedo.livrorecomendacao.entity.Aluno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AlunoMapper {

    AlunoMapper INSTANCE = Mappers.getMapper(AlunoMapper.class);

    @Mapping(target = "matricula", source = "matricula")
    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "telefone", source = "telefone")
    @Mapping(target = "turma", source = "turma")
    @Mapping(target = "turno", source = "turno")
    void updateAlunoFromDto(AlunoAtualizacaoDTO dto, @MappingTarget Aluno aluno);

}
