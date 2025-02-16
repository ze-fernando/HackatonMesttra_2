package com.mesttra.vacinas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mesttra.vacinas.config.ConexaoBanco;
import com.mesttra.vacinas.models.Vacina;
import com.mesttra.vacinas.models.Vacina.Publico_alvo;

public class VacinaDAO {
	public static List<Vacina> consultarTodasVacinas() throws SQLException{
    	List<Vacina> lista = new ArrayList<>();
    	
    	String sql = "SELECT * FROM vacinas";
    	
    	try (Connection conexao = ConexaoBanco.getConnection();
    		 Statement comando = conexao.createStatement();
			 ResultSet resultado = comando.executeQuery(sql)) {
    		
    		while (resultado.next()) {
    			
    			lista.add(new Vacina(	
					resultado.getInt("id_dose"),
					resultado.getString("vacina"),
					resultado.getString("dose"),
					resultado.getInt("idade_recomendada_aplicacao"),
					resultado.getInt("limite_aplicacao"),
					Publico_alvo.valueOf(resultado.getString("publico_alvo"))));					   									
    		}    		    			    					
		}
    	return lista;
    }
	
	public static List<Vacina> consultarTodasVacinasPorFaixaEtaria(Publico_alvo publicoAlvo) throws SQLException {
	    String sql = "SELECT * FROM vacinas WHERE publico_alvo = ?";

	    List<Vacina> vacinas = new ArrayList<>(); 
	    try (Connection conexao = ConexaoBanco.getConnection();
	         PreparedStatement comando = conexao.prepareStatement(sql)) {

	        comando.setString(1, publicoAlvo.name());

	        try (ResultSet resultado = comando.executeQuery()) {
	            while (resultado.next()) {
	                vacinas.add(new Vacina(
	                    resultado.getInt("id_dose"),
	                    resultado.getString("vacina"),
	                    resultado.getString("dose"),
	                    resultado.getInt("idade_recomendada_aplicacao"),
	                    resultado.getInt("limite_aplicacao"),
	                    Publico_alvo.valueOf(resultado.getString("publico_alvo"))));
	            }
	        }
	    }
	    return vacinas; 
	}

    public static List<Vacina> consultarTodasVacinasPorIdadeMaior(int meses) throws SQLException {
        String sql = "SELECT * FROM vacinas WHERE idade_recomendada_aplicacao > ?";

        List<Vacina> vacinas = new ArrayList<>();

        try (Connection conexao = ConexaoBanco.getConnection();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, meses);

            try (ResultSet resultado = comando.executeQuery()) {
                while (resultado.next()) {
                    vacinas.add(new Vacina(
                            resultado.getInt("id_dose"),
                            resultado.getString("vacina"),
                            resultado.getString("dose"),
                            resultado.getInt("idade_recomendada_aplicacao"),
                            resultado.getInt("limite_aplicacao"),
                            Publico_alvo.valueOf(resultado.getString("publico_alvo"))));
                }
            }
        }

        return vacinas;
    }
	
    //TODO: Implementar o metodo consultarTodasVacinasNaoAplicaveis(int idPaciente)
}
