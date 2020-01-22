package br.com.microservice.loja.controller.dto;

public class InfoPedidoDto {
	private Long id;
	private Integer tempoDePreparo;

	public InfoPedidoDto(Long id, Integer tempoDePreparo) {
		super();
		this.id = id;
		this.tempoDePreparo = tempoDePreparo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTempoDePreparo() {
		return tempoDePreparo;
	}

	public void setTempoDePreparo(Integer tempoDePreparo) {
		this.tempoDePreparo = tempoDePreparo;
	}

}
