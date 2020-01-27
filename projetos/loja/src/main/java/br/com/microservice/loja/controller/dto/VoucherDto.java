package br.com.microservice.loja.controller.dto;

import java.time.LocalDate;

public class VoucherDto {
	private Long numero;
	private LocalDate previsaoParaEntrega;

	
	public VoucherDto() {
		super();
	}

	public VoucherDto(Long numero, LocalDate previsaoParaEntrega) {
		super();
		this.numero = numero;
		this.previsaoParaEntrega = previsaoParaEntrega;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public LocalDate getPrevisaoParaEntrega() {
		return previsaoParaEntrega;
	}

	public void setPrevisaoParaEntrega(LocalDate previsaoParaEntrega) {
		this.previsaoParaEntrega = previsaoParaEntrega;
	}

}
