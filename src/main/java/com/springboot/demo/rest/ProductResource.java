package com.springboot.demo.rest;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.demo.model.Produto;
import com.springboot.demo.service.ProdutoService;

@RestController
@RequestMapping("/rest/produto")
public class ProductResource {

	@Autowired
	ProdutoService produtoService;
	
	@RequestMapping
	public ResponseEntity<List<Produto>> listar(){
		List<Produto> lista = produtoService.listar();
		if(!lista.isEmpty()){
			return ResponseEntity.ok().body(lista);
		}else{
			return ResponseEntity.notFound().build();
		}
			
	}
	@RequestMapping("/{id}")
	public ResponseEntity<Produto> buscarUnico(@PathVariable("id") Long id){
		Produto produto = produtoService.buscar(id);
		return ResponseEntity.ok().body(produto);
	}
	
	@GetMapping(params="nome")
	public ResponseEntity<List<Produto>> buscarPorNome(@RequestParam(value="nome") String nome){
		List<Produto> produtos = produtoService.buscarPorNome(nome);
		return ResponseEntity.ok().body(produtos);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Produto produto){
		Produto p = produtoService.salvar(produto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(p.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
}
