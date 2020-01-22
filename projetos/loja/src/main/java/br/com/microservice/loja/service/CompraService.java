package br.com.microservice.loja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.microservice.loja.controller.dto.CompraDto;
import br.com.microservice.loja.controller.dto.InfoFornecedorDto;
import br.com.microservice.loja.controller.dto.InfoPedidoDto;
import br.com.microservice.loja.model.Compra;
import br.com.microservice.loja.service.client.FornecedorClient;

@Service
public class CompraService {

	@Autowired
	private FornecedorClient fornecedorClient;
	
	public Compra realizaCompra(CompraDto compra) {
		InfoFornecedorDto info = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());
		InfoPedidoDto pedido = fornecedorClient.realizaPedido(compra.getItens());
		System.out.println(info.getEndereco());
		Compra compraSalva = new Compra(pedido.getId(), pedido.getTempoDePreparo(), compra.getEndereco().toString());
		return compraSalva;
	}

}
