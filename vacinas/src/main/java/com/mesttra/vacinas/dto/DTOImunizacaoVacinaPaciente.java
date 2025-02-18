package com.mesttra.vacinas.dto;

public class DTOImunizacaoVacinaPaciente {
	public int id;
	public int qtdVacinasAplicadas;
	public int qtdeVacinasParaProximoMes;
	public int qtdVacinasNaoAplicadas;
	public int qtdeVacinasAcimaDaIdade;
	public int qtdVacinasNaoAplicaveis;
	
	public DTOImunizacaoVacinaPaciente(int id, int qtdVacinasAplicadas) {
		
		this.id = id;
		this.qtdVacinasAplicadas = qtdVacinasAplicadas;
	}
	
	
}
