package br.com.microservice.loja.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.microservice.loja.controller.dto.CompraDto;
import br.com.microservice.loja.controller.dto.InfoFornecedorDto;
import br.com.microservice.loja.controller.dto.InfoPedidoDto;
import br.com.microservice.loja.model.Compra;
import br.com.microservice.loja.repository.CompraRepository;
import br.com.microservice.loja.service.client.FornecedorClient;

@Service
public class CompraService {

	private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);

	@Autowired
	private FornecedorClient fornecedorClient;

	@Autowired
	private CompraRepository compraRepository;

	@HystrixCommand(fallbackMethod = "realizaCompraFallback", threadPoolKey = "realizaCompraThreadPool")
	public Compra realizaCompra(CompraDto compra) {
		LOG.info("Buscando informações do fornecedor de {}", compra.getEndereco().getEstado());
		InfoFornecedorDto info = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());
		InfoPedidoDto pedido = fornecedorClient.realizaPedido(compra.getItens());
		System.out.println(info.getEndereco());
		LOG.info("Realizando o Pedido");
		Compra compraSalva = compraRepository.save(new Compra(pedido.getId(), pedido.getTempoDePreparo(), compra.getEndereco().toString()));
		return compraSalva;
	}

	@HystrixCommand(threadPoolKey = "getByIdThreadPool")
	public Compra getById(Long id) {
		return compraRepository.findById(id).orElse(new Compra());
	}

	public Compra realizaCompraFallback(CompraDto compra) {
		Compra compraFallback = new Compra();
		compraFallback.setEndereco(compra.getEndereco().toString());
		return compraFallback;
	}

}
