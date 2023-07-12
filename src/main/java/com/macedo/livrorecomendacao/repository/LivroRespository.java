package com.macedo.livrorecomendacao.repository;

import com.macedo.livrorecomendacao.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRespository extends JpaRepository<Livro, Long> {
    @Query("SELECT e FROM Livro e WHERE e.isbn = ?1")
    Livro findByIsbn(String isbn);
}
