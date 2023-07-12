package com.macedo.livrorecomendacao.repository;

import com.macedo.livrorecomendacao.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    @Query("SELECT e FROM Aluno e WHERE e.matricula = ?1")
    Aluno findByMatricula(String matricula);
}
