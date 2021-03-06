package br.com.microservice.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.microservice.loja.controller.dto.CompraDto;
import br.com.microservice.loja.model.Compra;
import br.com.microservice.loja.service.CompraService;

@RestController
@RequestMapping("/compra")
public class CompraController {
	@Autowired
	private CompraService compraService;

	@RequestMapping(method = RequestMethod.POST)
	public Compra realizaCompra(@RequestBody CompraDto compra) {
		return compraService.realizaCompra(compra);
	}

	@RequestMapping("/{id}")
	public Compra getById(@PathVariable Long id) {
		return compraService.getById(id);
	}
}
