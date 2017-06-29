package com.springboot.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.demo.model.Produto;
import com.springboot.demo.repository.ProdutoRepository;
import com.springboot.demo.rest.exception.EntidadeNaoEncontradaException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	public List<Produto> listar() {
		return produtoRepository.findAll();
	}

	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}
	public void excluir(Long id) {
		Produto p = new Produto();
		p.setId(id);
		
		produtoRepository.delete(p);
	}
	public Produto buscar(Long id){
		Produto produto = produtoRepository.findOne(id);
		if(produto == null){
			throw new EntidadeNaoEncontradaException("Não foi encontrado nenhum produto com este identificador");
		}
		return produto;
	}

	public List<Produto> buscarPorNome(String nome) {
		List<Produto> produtos = produtoRepository.findByNomeContainingIgnoreCase(nome);
		if(produtos == null || produtos.isEmpty()){
			throw new EntidadeNaoEncontradaException("Não foi encontrado nenhum produto com nome parecido");
		}
		return produtos;
	}
	
	
}
