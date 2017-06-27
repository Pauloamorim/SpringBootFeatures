package com.springboot.demo.rest.handler;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.springboot.demo.model.ErroWS;
import com.springboot.demo.rest.exception.EntidadeNaoEncontradaException;

@ControllerAdvice
public class RuntimeExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ErroWS> handleServicoException(EntidadeNaoEncontradaException e, HttpServletRequest request) {
		ErroWS erroWS = new ErroWS();
		erroWS.setStatus(404);
		erroWS.setMensagem(e.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroWS);
	}
}
