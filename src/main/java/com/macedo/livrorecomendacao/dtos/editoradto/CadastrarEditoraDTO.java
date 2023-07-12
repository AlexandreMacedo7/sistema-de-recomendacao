package com.macedo.livrorecomendacao.dtos.editoradto;

import jakarta.validation.constraints.NotBlank;

public record CadastrarEditoraDTO(
        @NotBlank
        String nome) {
}
