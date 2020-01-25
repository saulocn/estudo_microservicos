package br.com.microservice.loja.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Compra {
	
	@Id
	private Long pedidoId;
	private Integer tempoDePreparo;
	private String endereco;

	public Compra() {
		super();	
	}

	public Compra(Long pedidoId, Integer tempoDePreparo, String endereco) {
		super();
		this.pedidoId = pedidoId;
		this.tempoDePreparo = tempoDePreparo;
		this.endereco = endereco;
	}

	public Long getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}

	public Integer getTempoDePreparo() {
		return tempoDePreparo;
	}

	public void setTempoDePreparo(Integer tempoDePreparo) {
		this.tempoDePreparo = tempoDePreparo;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
