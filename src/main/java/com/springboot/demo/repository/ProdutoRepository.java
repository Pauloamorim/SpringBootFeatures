package com.springboot.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.demo.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
