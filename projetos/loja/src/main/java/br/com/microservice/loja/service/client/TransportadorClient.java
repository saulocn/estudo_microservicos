package br.com.microservice.loja.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.microservice.loja.controller.dto.InfoEntregaDto;
import br.com.microservice.loja.controller.dto.VoucherDto;

@FeignClient("transportador")
public interface TransportadorClient {

	@RequestMapping(method = RequestMethod.POST, value="/entrega")
	public VoucherDto reservaEntrega(InfoEntregaDto entregaDto);

}
