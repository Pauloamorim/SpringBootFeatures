package com.springboot.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.demo.model.Categoria;
import com.springboot.demo.service.CategoriaService;

@Controller
@RequestMapping(value = "/categoria")
public class categoriaController {

	private static final String PAGINA_PESQUISA_CATEGORIA = "categoria/categoria";
	private static final String PAGINA_MANTER_CATEGORIA = "categoria/manter_categoria";

	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping
	public ModelAndView iniciarCategoria() {
		List<Categoria> categorias = categoriaService.listar();

		ModelAndView mv = new ModelAndView(PAGINA_PESQUISA_CATEGORIA);
		mv.addObject("categorias", categorias);
		return mv;
	}

	@RequestMapping("/novo")
	public ModelAndView criarCategoria() {
		ModelAndView mv = new ModelAndView(PAGINA_MANTER_CATEGORIA);
		mv.addObject("categoria", new Categoria());
		return mv;
	}
	@RequestMapping("{id}")
	public ModelAndView carregarAlterar(@PathVariable("id") Categoria categoria) {
		ModelAndView mv = new ModelAndView(PAGINA_MANTER_CATEGORIA);
		mv.addObject("categoria", categoria);
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Categoria categoria, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return PAGINA_MANTER_CATEGORIA;
		}
		categoriaService.salvar(categoria);
		attributes.addFlashAttribute("mensagem", "Categoria salva com sucesso!");
		return "redirect:categoria";
	}
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id, RedirectAttributes attributes) {
		categoriaService.excluir(id);
		
		attributes.addFlashAttribute("mensagem", "Categoria excluída com sucesso!");
		return "redirect:/categoria";
	}

}
