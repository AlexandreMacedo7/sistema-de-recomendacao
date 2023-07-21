package com.macedo.livrorecomendacao.repository;

import com.macedo.livrorecomendacao.entity.Avaliacao;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Avaliacao a WHERE a.aluno.matricula = :matricula AND a.livro.isbn = :isbn")
    void deleteByMatriculaEIsbn(String matricula, String isbn);
}
