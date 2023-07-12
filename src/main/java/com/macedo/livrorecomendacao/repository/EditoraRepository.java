package com.macedo.livrorecomendacao.repository;

import com.macedo.livrorecomendacao.dtos.editoradto.CadastrarEditoraDTO;
import com.macedo.livrorecomendacao.entity.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> {
    @Query("SELECT e FROM Editora e WHERE e.nome = ?1")
    Editora findByNome(CadastrarEditoraDTO editoraDTO);
}
