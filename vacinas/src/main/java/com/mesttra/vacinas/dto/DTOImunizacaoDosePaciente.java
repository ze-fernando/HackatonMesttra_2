package com.mesttra.vacinas.dto;

import java.sql.Date;

public class DTOImunizacaoDosePaciente {
	public int id;
	public String nome;
	public String vacina;
	public String dose;
	public Date data_aplicacao;
	public String fabricante;
    public String lote;
    public String local_aplicacao;
    public String profissional_aplicador;
    
	public DTOImunizacaoDosePaciente(int id, String nome, String vacina, String dose, Date data_aplicacao,
			String fabricante, String lote, String local_aplicacao, String profissional_aplicador) {
		
		this.id = id;
		this.nome = nome;
		this.vacina = vacina;
		this.dose = dose;
		this.data_aplicacao = data_aplicacao;
		this.fabricante = fabricante;
		this.lote = lote;
		this.local_aplicacao = local_aplicacao;
		this.profissional_aplicador = profissional_aplicador;
	}	
    
    
}
