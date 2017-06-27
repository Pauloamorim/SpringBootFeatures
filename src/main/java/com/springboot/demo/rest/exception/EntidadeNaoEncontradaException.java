package com.springboot.demo.rest.exception;

public class EntidadeNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 5282554215169852907L;
	
	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

}
