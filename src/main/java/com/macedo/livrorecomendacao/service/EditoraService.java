package com.macedo.livrorecomendacao.service;

import com.macedo.livrorecomendacao.dtos.editoradto.CadastrarEditoraDTO;
import com.macedo.livrorecomendacao.entity.Editora;
import com.macedo.livrorecomendacao.repository.EditoraRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class EditoraService {

    private final EditoraRepository editoraRepository;

    public EditoraService(EditoraRepository editoraRepository){
        this.editoraRepository = editoraRepository;
    }

    public Editora criarEditora(@Valid CadastrarEditoraDTO editoraDTO){

        Editora editora = editoraRepository.findByNome(editoraDTO.nome());
        if (editora == null){
            editora = new Editora(editoraDTO);
            editoraRepository.save(editora);
        }
        return editora;
    };
}
