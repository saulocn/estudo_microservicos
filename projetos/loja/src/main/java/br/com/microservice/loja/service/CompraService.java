package br.com.microservice.loja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.microservice.loja.controller.dto.CompraDto;
import br.com.microservice.loja.controller.dto.InfoFornecedorDto;

@Service
public class CompraService {

	@Autowired
	private RestTemplate client; 
	
	public void realizaCompra(CompraDto compra) {
		ResponseEntity<InfoFornecedorDto> infoFornecedor = client.exchange("http://fornecedor/info/" + 
				compra.getEndereco().getEstado(), 
				HttpMethod.GET, null, InfoFornecedorDto.class);
		System.out.println(infoFornecedor.getBody().getEndereco());

	}

}
