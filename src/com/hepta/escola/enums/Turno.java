package com.hepta.escola.enums;

public enum Turno {
	MATUTINO("Matutino"),VESPERTINO("Vespertino"),NOTURNO("Noturno");
	
	
	private String desc;

	
	private Turno(String descricao) {
		setDesc(descricao);
	}
	
	
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
