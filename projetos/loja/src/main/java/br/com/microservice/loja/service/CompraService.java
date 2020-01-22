package br.com.microservice.loja.service;

import org.bouncycastle.est.ESTAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.microservice.loja.controller.dto.CompraDto;
import br.com.microservice.loja.controller.dto.InfoFornecedorDto;
import br.com.microservice.loja.controller.dto.InfoPedidoDto;
import br.com.microservice.loja.model.Compra;
import br.com.microservice.loja.service.client.FornecedorClient;

@Service
public class CompraService {
	
	private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);

	@Autowired
	private FornecedorClient fornecedorClient;
	
	public Compra realizaCompra(CompraDto compra) {
		LOG.info("Buscando informações do fornecedor de {}", compra.getEndereco().getEstado());
		InfoFornecedorDto info = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());
		InfoPedidoDto pedido = fornecedorClient.realizaPedido(compra.getItens());
		System.out.println(info.getEndereco());
		LOG.info("Realizando o Pedido");		
		Compra compraSalva = new Compra(pedido.getId(), pedido.getTempoDePreparo(), compra.getEndereco().toString());
		return compraSalva;
	}

}
