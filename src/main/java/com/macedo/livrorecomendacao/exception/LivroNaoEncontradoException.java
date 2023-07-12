package com.macedo.livrorecomendacao.exception;

public class LivroNaoEncontradoException extends RuntimeException{

    public LivroNaoEncontradoException(String msg){
        super(msg);
    }
}
