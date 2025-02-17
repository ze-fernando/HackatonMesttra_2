package com.mesttra.vacinas.dto;

public class DTOVacinaDose {
	public int id;
	public String vacina;
	public String dose;
	public int idade_recomendada_aplicacao;
	public int limite_aplicacao;
	public PublicoAlvo  publico_alvo;
    
    public enum PublicoAlvo {
    	CRIANÇA, 
    	ADOLESCENTE, 
    	ADULTO, 
    	GESTANTE
    }

	public DTOVacinaDose(int id, String vacina, String dose, int idade_recomendada_aplicacao, int limite_aplicacao,
			PublicoAlvo publico_alvo) {
		
		this.id = id;
		this.vacina = vacina;
		this.dose = dose;
		this.idade_recomendada_aplicacao = idade_recomendada_aplicacao;
		this.limite_aplicacao = limite_aplicacao;
		this.publico_alvo = publico_alvo;
	}

	public DTOVacinaDose(int id, String vacina, String dose, int limite_aplicacao) {
		
		this.id = id;
		this.vacina = vacina;
		this.dose = dose;
		this.limite_aplicacao = limite_aplicacao;
	}
    
    
}
