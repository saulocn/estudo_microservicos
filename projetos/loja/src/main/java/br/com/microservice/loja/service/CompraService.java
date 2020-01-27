package br.com.microservice.loja.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.microservice.loja.controller.dto.CompraDto;
import br.com.microservice.loja.controller.dto.InfoEntregaDto;
import br.com.microservice.loja.controller.dto.InfoFornecedorDto;
import br.com.microservice.loja.controller.dto.InfoPedidoDto;
import br.com.microservice.loja.controller.dto.VoucherDto;
import br.com.microservice.loja.model.Compra;
import br.com.microservice.loja.model.CompraState;
import br.com.microservice.loja.repository.CompraRepository;
import br.com.microservice.loja.service.client.FornecedorClient;
import br.com.microservice.loja.service.client.TransportadorClient;

@Service
public class CompraService {

	private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);

	@Autowired
	private FornecedorClient fornecedorClient;

	@Autowired
	private TransportadorClient transportadorClient;

	@Autowired
	private CompraRepository compraRepository;

	@HystrixCommand(fallbackMethod = "realizaCompraFallback", threadPoolKey = "realizaCompraThreadPool")
	public Compra realizaCompra(CompraDto compra) {
		Compra compraSalva = new Compra();
		compraSalva.setEndereco(compra.getEndereco().toString());
		compraSalva.setState(CompraState.RECEBIDO);
		compraSalva = compraRepository.save(compraSalva);
		compra.setCompraId(compraSalva.getId());
		LOG.info("Buscando informações do fornecedor de {}", compra.getEndereco().getEstado());
		InfoFornecedorDto info = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());
		InfoPedidoDto pedido = fornecedorClient.realizaPedido(compra.getItens());
		compraSalva.setState(CompraState.PEDIDO_REALIZADO);
		compraSalva.setPedidoId(pedido.getId());
		compraSalva.setTempoDePreparo(pedido.getTempoDePreparo());
		compraSalva = compraRepository.save(compraSalva);
		InfoEntregaDto entregaDto = new InfoEntregaDto(pedido.getId(),
				LocalDate.now().plusDays(pedido.getTempoDePreparo()), info.getEndereco(),
				compra.getEndereco().toString());
		VoucherDto voucher = transportadorClient.reservaEntrega(entregaDto);
		compraSalva.setDataParaEntrega(voucher.getPrevisaoParaEntrega());
		compraSalva.setVoucher(voucher.getNumero());
		compraSalva.setState(CompraState.RESERVA_ENTREGA_REALIZADA);
		compraSalva = compraRepository.save(compraSalva);
		LOG.info("Realizando o Pedido");
		compraSalva = compraRepository.save(compraSalva);
		return compraSalva;
	}

	@HystrixCommand(threadPoolKey = "getByIdThreadPool")
	public Compra getById(Long id) {
		return compraRepository.findById(id).orElse(new Compra());
	}

	public Compra realizaCompraFallback(CompraDto compra) {
		if (compra.getCompraId() != null) {
			return compraRepository.findById(compra.getCompraId()).orElse(new Compra());
		}
		Compra compraFallback = new Compra();
		compraFallback.setEndereco(compra.getEndereco().toString());
		return compraFallback;
	}

}
