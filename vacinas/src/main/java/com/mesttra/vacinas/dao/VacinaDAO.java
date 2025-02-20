package com.mesttra.vacinas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mesttra.vacinas.config.ConexaoBanco;
import com.mesttra.vacinas.dto.DTOVacinaDose;
import com.mesttra.vacinas.models.Vacina;
import com.mesttra.vacinas.models.enums.PublicoAlvo;

public class VacinaDAO {
	public static List<Vacina> consultarTodasVacinas() throws SQLException{
    	List<Vacina> lista = new ArrayList<>();
    	
    	String sql = "SELECT * FROM vacina";
    	
    	try (Connection conexao = ConexaoBanco.getConnection();
    		 Statement comando = conexao.createStatement();
			 ResultSet resultado = comando.executeQuery(sql)) {
    		
    		while (resultado.next()) {
    			
    			lista.add(new Vacina(	
					resultado.getInt("id_dose"),
					resultado.getString("vacina"),
					resultado.getString("descricao"),
					resultado.getInt("limite_aplicacao"),
					PublicoAlvo.valueOf(resultado.getString("publico_alvo"))));					   									
    		}    		    			    					
		}
    	return lista;
    }
	
	public static List<DTOVacinaDose> consultarTodasVacinasPorFaixaEtaria(PublicoAlvo publicoAlvo) throws SQLException {
	    String sql = "SELECT " 
	    	    + "d.id, "
	    	    + "v.vacina, "
	    	    + "d.dose, " 
	    	    + "d.idade_recomendada_aplicacao, "
	    	    + "v.limite_aplicacao, "
	    	    + "v.publico_alvo  " 
	    	    + "FROM dose d "	    	     
	    	    + "JOIN vacina v ON d.id_vacina = v.id "
	    	    + "WHERE v.publico_alvo = ?";
	    	 	    	    
	    List<DTOVacinaDose> vacinas = new ArrayList<>(); 
	    try (Connection conexao = ConexaoBanco.getConnection();
	         PreparedStatement comando = conexao.prepareStatement(sql)) {

	        comando.setString(1, publicoAlvo.name());

	        try (ResultSet resultado = comando.executeQuery()) {
	            while (resultado.next()) {
	                vacinas.add(new DTOVacinaDose(
	                    resultado.getInt("id"),
	                    resultado.getString("vacina"),
	                    resultado.getString("dose"),
	                    resultado.getInt("idade_recomendada_aplicacao"),
	                    resultado.getInt("limite_aplicacao"),
	                    PublicoAlvo.valueOf(resultado.getString("publico_alvo"))));
	            }
	        }
	    }
	    return vacinas; 
	}

    public static List<DTOVacinaDose> consultarTodasVacinasPorIdadeMaior(int meses) throws SQLException {
    	String sql = "SELECT "
                + "v.id, "
                + "v.vacina, "
                + "v.descricao, "
                + "d.dose, "
                + "d.idade_recomendada_aplicacao "
                + "FROM vacina v "
                + "JOIN dose d ON d.id_vacina = v.id "
                + "WHERE d.idade_recomendada_aplicacao > ?";


        List<DTOVacinaDose> vacinas = new ArrayList<>();

        try (Connection conexao = ConexaoBanco.getConnection();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, meses);

            try (ResultSet resultado = comando.executeQuery()) {
                while (resultado.next()) {
                    vacinas.add(new DTOVacinaDose(
                            resultado.getInt("id"),
                            resultado.getString("vacina"),
                            resultado.getString("dose"),
                            resultado.getInt("idade_recomendada_aplicacao"),
                            resultado.getInt("limite_aplicacao"),
                            PublicoAlvo.valueOf(resultado.getString("publico_alvo"))));
                }
            }
        }
        return vacinas;
    }
	
    public static List<DTOVacinaDose>consultarTodasVacinasNaoAplicaveis(int idPaciente) throws SQLException{
    	String sql = "SELECT " 
                + "d.dose, " 
                + "v.vacina, " 
                + "d.idade_recomendada_aplicacao AS limite_aplicacao "
                + "FROM vacina v "
                + "JOIN dose d ON d.id_vacina = v.id "
                + "JOIN paciente p ON p.id = ? "
                + "WHERE TIMESTAMPDIFF(MONTH, p.data_nascimento, CURDATE()) > d.idade_recomendada_aplicacao";


        List<DTOVacinaDose> vacinas = new ArrayList<>();

        try (Connection conexao = ConexaoBanco.getConnection();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, idPaciente);

            try (ResultSet resultado = comando.executeQuery()) {
                while (resultado.next()) {
                    vacinas.add(new DTOVacinaDose(
                            resultado.getInt("id"),
                            resultado.getString("vacina"),
                            resultado.getString("dose"),                           
                            resultado.getInt("limite_aplicacao")));
                            
                }
            }
        }
        return vacinas;
    }
}
