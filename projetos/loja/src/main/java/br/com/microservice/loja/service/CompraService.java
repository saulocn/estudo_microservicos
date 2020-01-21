package br.com.microservice.loja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.microservice.loja.controller.dto.CompraDto;
import br.com.microservice.loja.controller.dto.InfoFornecedorDto;
import br.com.microservice.loja.service.client.FornecedorClient;

@Service
public class CompraService {

	@Autowired
	private FornecedorClient fornecedorClient;
	
	public void realizaCompra(CompraDto compra) {
		InfoFornecedorDto info = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());	
		System.out.println(info	.getEndereco());

	}

}
