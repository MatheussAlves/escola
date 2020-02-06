package com.hepta.escola.enums;

public enum Ensino {
	MATERNAL("Maternal"),FUNDAMENTAL("Ensino Fundamental 1"),FUNDAMENTAL2("Ensino Funamental 2"),MEDIO("Ensino medio");
	
	private String desc;
	
	private Ensino(String descricao) {
		setDesc(descricao);
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	

}
