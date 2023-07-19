package com.macedo.livrorecomendacao.dtos.editoradto;

import jakarta.validation.constraints.NotBlank;

public record EditoraDTO(@NotBlank String nome) {
}
