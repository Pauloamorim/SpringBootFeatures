package com.springboot.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.demo.model.Categoria;
import com.springboot.demo.model.Produto;
import com.springboot.demo.service.CategoriaService;
import com.springboot.demo.service.ProdutoService;

@Controller
@RequestMapping(value = "/produto")
public class ProdutoController {

	private static final String PAGINA_PESQUISA_PRODUTO = "produto/produto";
	private static final String PAGINA_MANTER_PRODUTO = "produto/manter_produto";

	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping
	public ModelAndView iniciarCategoria() {
		List<Produto> categorias = produtoService.listar();

		ModelAndView mv = new ModelAndView(PAGINA_PESQUISA_PRODUTO);
		mv.addObject("produtos", categorias);
		return mv;
	}

	@RequestMapping("/novo")
	public ModelAndView criarProduto() {
		ModelAndView mv = new ModelAndView(PAGINA_MANTER_PRODUTO);
		mv.addObject("produto", new Produto());
		return mv;
	}
	@RequestMapping("{id}")
	public ModelAndView carregarAlterar(@PathVariable("id") Produto produto) {
		ModelAndView mv = new ModelAndView(PAGINA_MANTER_PRODUTO);
		mv.addObject("produto", produto);
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Produto produto, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return PAGINA_MANTER_PRODUTO;
		}
		produtoService.salvar(produto);
		attributes.addFlashAttribute("mensagem", "Produto salvo com sucesso!");
		return "redirect:produto";
	}
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
		produtoService.excluir(id);
		
		attributes.addFlashAttribute("mensagem", "Produto excluído com sucesso!");
		return "redirect:/produto";
	}
	
	@ModelAttribute("categorias")
	public List<Categoria> todosStatusTitulo() {
		return categoriaService.listar();
	}

}
