package com.springboot.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.demo.model.Categoria;
import com.springboot.demo.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}

	public void salvar(Categoria categoria) {
		categoriaRepository.save(categoria);
	}
	public void excluir(Integer id) {
		Categoria c = new Categoria(id,null);
		categoriaRepository.delete(c);
	}
	
}
