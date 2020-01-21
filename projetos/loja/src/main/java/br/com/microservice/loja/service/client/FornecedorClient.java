package br.com.microservice.loja.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.microservice.loja.controller.dto.InfoFornecedorDto;

@FeignClient("fornecedor")
public interface FornecedorClient {
	
	@RequestMapping("/info/{estado}")
	InfoFornecedorDto getInfoPorEstado(@PathVariable String estado);
}
