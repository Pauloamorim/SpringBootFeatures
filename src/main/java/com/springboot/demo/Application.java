package com.springboot.demo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import com.springboot.demo.model.Categoria;
import com.springboot.demo.model.Produto;
import com.springboot.demo.repository.CategoriaRepository;
import com.springboot.demo.repository.ProdutoRepository;

@SpringBootApplication
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner setup(CategoriaRepository categoriaRepository,ProdutoRepository produtoRepository) {
		return (args) -> {
			categoriaRepository.save(new Categoria(null,"Carro"));
			categoriaRepository.save(new Categoria(null,"Alimentos"));
			categoriaRepository.save(new Categoria(null,"Informática"));
			categoriaRepository.save(new Categoria(null,"Esportes"));
			
			produtoRepository.save(new Produto(null,"Mouse Razer",new BigDecimal("150"),new Date(),new Categoria(1,null)));
			
			
			logger.info("Criando categorias default");
		};
	}
	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
}
