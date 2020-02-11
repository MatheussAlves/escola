package com.hepta.escola.enums;

public enum TipoUsuario {
	ADMINISTRADOR("Administrador"),ESTUDANTE("Estudante"),DOCENTE("Docente");
	
	private String descricao;
	
	private TipoUsuario(String desc) {
		setDescricao(desc);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}	
