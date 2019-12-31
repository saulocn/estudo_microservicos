package br.com.microserice.loja.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.microserice.loja.controller.dto.CompraDto;

@RestController
@RequestMapping("/compra")
public class CompraController {

	@RequestMapping(method = RequestMethod.POST)
	public void realizaCompra(@RequestBody CompraDto compra) {
	}
}
