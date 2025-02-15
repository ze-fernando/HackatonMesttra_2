package com.mesttra.vacinas.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mesttra.vacinas.config.ConexaoBanco;	
import com.mesttra.vacinas.models.Imunizacoes;
import com.mesttra.vacinas.models.Paciente;
import com.mesttra.vacinas.models.Paciente.Sexo;

public class ImunizacoesDAO {
	public static void adicionarImunizacao(Imunizacoes imunizacao) throws SQLException{
        String sql = "INSERT INTO imunizacoes (id_paciente, id_dose, data_aplicacao, fabricante, lote, "
        		+ "local_aplicacao, profissional_aplicador) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conexao = ConexaoBanco.getConnection();
             PreparedStatement comando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        	comando.setInt(1, imunizacao.getIdPaciente());  
            comando.setInt(2, imunizacao.getIdDose());     
            comando.setDate(3, new java.sql.Date(imunizacao.getDataAplicacao().getTime()));
            comando.setString(4, imunizacao.getFabricante());
            comando.setString(5, imunizacao.getLote()); 
            comando.setString(6, imunizacao.getLocalAplicacao());
            comando.setString(7, imunizacao.getProfissionalAplicador());
            
            comando.executeUpdate();
            
            try (ResultSet resultado = comando.getGeneratedKeys()) {
                if (resultado.next()) {
                    int idGerado = resultado.getInt(1);
                    imunizacao.setId(idGerado);
                    
                    System.out.println("Imunização inserida com ID: " + idGerado);
                }
            }
        }
    }
	
	public static void alterarImunizacao(Imunizacoes imunizacao) throws SQLException {
    	String sql = "UPDATE imunizacoes SET data_aplicacao = ?, fabricante = ?, lote = ?, local_aplicacao = ?, "
    			+ "profissional_aplicador = ? WHERE id = ?";
    	
    	try (Connection conexao = ConexaoBanco.getConnection();
			 PreparedStatement comando = conexao.prepareStatement(sql)){
    		
    		comando.setDate(1, new java.sql.Date(imunizacao.getDataAplicacao().getTime()));
            comando.setString(2, imunizacao.getFabricante());
            comando.setString(3, imunizacao.getLote());         
            comando.setString(4, imunizacao.getLocalAplicacao()); 
            comando.setString(5, imunizacao.getProfissionalAplicador()); 
            comando.setString(6, imunizacao.getLocalAplicacao()); 
            comando.setInt(7, imunizacao.getId()); 
            
            comando.executeUpdate();
        }
    }
	
	public static void excluirImunizacao(int id) throws SQLException {
    	String sql = "DELETE FROM imunizacoes WHERE id = ?";
    	
    	try (Connection conexao = ConexaoBanco.getConnection();
			 PreparedStatement comando = conexao.prepareStatement(sql)){
			
    		comando.setInt(1, id);
    		
    		comando.executeUpdate();
		}
    }
	
	public static void excluirTodasImunizacoesDoPaciente(int idPaciente) throws SQLException {
    	String sql = "DELETE FROM imunizacoes WHERE id_paciente = ?";
    	
    	try (Connection conexao = ConexaoBanco.getConnection();
			 PreparedStatement comando = conexao.prepareStatement(sql)){
			
    		comando.setInt(1, idPaciente);
    		
    		comando.executeUpdate();
		}
    }
	
	public static List<Imunizacoes> consultarTodasImunizacoes() throws SQLException{
    	List<Imunizacoes> lista = new ArrayList<>();
    	
    	String sql = "SELECT "
    			+ "i.id, "
                + "p.nome, "
                + "v.vacina, "
                + "d.dose, "
                + "i.data_aplicacao, "
                + "i.fabricante, "
                + "i.lote, "
                + "i.local_aplicacao, "
                + "i.profissional_aplicador "
                + "FROM imunizacoes i "
                + "JOIN pacientes p ON i.id_paciente = p.id "
                + "JOIN doses d ON i.id_dose = d.id "
                + "JOIN vacinas v ON d.id_vacina = v.id";
    	
    	try (Connection conexao = ConexaoBanco.getConnection();
    		 Statement comando = conexao.createStatement();
			 ResultSet resultado = comando.executeQuery(sql)) {
    		
    		while (resultado.next()) {
    			
    			lista.add(new Imunizacoes(	
					resultado.getInt("id"),
					resultado.getString("nome"),
					resultado.getString("vacina"),
					resultado.getString("dose"),
					resultado.getDate("data_aplicacao"),
					resultado.getString("fabricante"),
					resultado.getString("lote"),
					resultado.getString("local_aplicacao"),
					resultado.getString("profissional_aplicador")));
					   								   									
    		}    		    			    					
		}
    	return lista;
    }
	
	public static Imunizacoes consultarImunizacaoPorIdImunizacao(int idImunizacao) throws SQLException {
	    Imunizacoes imunizacao = null;
	    
	    String sql = "SELECT "
	            + "i.id, "
	            + "p.nome, "
	            + "v.vacina, "
	            + "d.dose, "
	            + "i.data_aplicacao, "
	            + "i.fabricante, "
	            + "i.lote, "
	            + "i.local_aplicacao, "
	            + "i.profissional_aplicador "
	            + "FROM imunizacoes i "
	            + "JOIN pacientes p ON i.id_paciente = p.id "
	            + "JOIN doses d ON i.id_dose = d.id "
	            + "JOIN vacinas v ON d.id_vacina = v.id "
	            + "WHERE i.id = ?";
	    
	    try (Connection conexao = ConexaoBanco.getConnection();
	         PreparedStatement comando = conexao.prepareStatement(sql)) {
	        
	        comando.setInt(1, idImunizacao);
	        
	        try (ResultSet resultado = comando.executeQuery()) {
	            if (resultado.next()) {
	                imunizacao = new Imunizacoes(
	                        resultado.getInt("id"),
	                        resultado.getString("nome"),
	                        resultado.getString("vacina"),
	                        resultado.getString("dose"),
	                        resultado.getDate("data_aplicacao"),
	                        resultado.getString("fabricante"),
	                        resultado.getString("lote"),
	                        resultado.getString("local_aplicacao"),
	                        resultado.getString("profissional_aplicador"));
	            }
	        }
	    }
	    
	    return imunizacao;
	}
	
	public static List<Imunizacoes> consultarImunizacoesPorIdPaciente(int idPaciente) throws SQLException {
	    List<Imunizacoes> lista = new ArrayList<>();
	    
	    String sql = "SELECT "
	            + "i.id, "
	            + "p.nome, "
	            + "v.vacina, "
	            + "d.dose, "
	            + "i.data_aplicacao, "
	            + "i.fabricante, "
	            + "i.lote, "
	            + "i.local_aplicacao, "
	            + "i.profissional_aplicador "
	            + "FROM imunizacoes i "
	            + "JOIN pacientes p ON i.id_paciente = p.id "
	            + "JOIN doses d ON i.id_dose = d.id "
	            + "JOIN vacinas v ON d.id_vacina = v.id "
	            + "WHERE i.id_paciente = ?";
	    
	    try (Connection conexao = ConexaoBanco.getConnection();
	         PreparedStatement comando = conexao.prepareStatement(sql)) {
	        
	        comando.setInt(1, idPaciente);
	        
	        try (ResultSet resultado = comando.executeQuery()) {
	            while (resultado.next()) {
	                lista.add(new Imunizacoes(
	                        resultado.getInt("id"),
	                        resultado.getString("nome"),
	                        resultado.getString("vacina"),
	                        resultado.getString("dose"),
	                        resultado.getDate("data_aplicacao"),
	                        resultado.getString("fabricante"),
	                        resultado.getString("lote"),
	                        resultado.getString("local_aplicacao"),
	                        resultado.getString("profissional_aplicador")
	                ));
	            }
	        }
	    }
	    
	    return lista;
	}
	
	public static List<Imunizacoes> consultarImunizacoesPorIdPacienteEPeriodo(int idPaciente, Date dtInicio, Date dtFim) throws SQLException {
	    List<Imunizacoes> lista = new ArrayList<>();
	    
	    String sql = "SELECT "
	            + "i.id, "
	            + "p.nome, "
	            + "v.vacina, "
	            + "d.dose, "
	            + "i.data_aplicacao, "
	            + "i.fabricante, "
	            + "i.lote, "
	            + "i.local_aplicacao, "
	            + "i.profissional_aplicador "
	            + "FROM imunizacoes i "
	            + "JOIN pacientes p ON i.id_paciente = p.id "
	            + "JOIN doses d ON i.id_dose = d.id "
	            + "JOIN vacinas v ON d.id_vacina = v.id "
	            + "WHERE i.id_paciente = ? "
	            + "AND i.data_aplicacao BETWEEN ? AND ?";
	    
	    try (Connection conexao = ConexaoBanco.getConnection();
	         PreparedStatement comando = conexao.prepareStatement(sql)) {
	        
	        comando.setInt(1, idPaciente);
	        comando.setDate(2, dtInicio);  
	        comando.setDate(3, dtFim);     
	        
	        try (ResultSet resultado = comando.executeQuery()) {
	            while (resultado.next()) {
	                lista.add(new Imunizacoes(
	                        resultado.getInt("id"),
	                        resultado.getString("nome"),
	                        resultado.getString("vacina"),
	                        resultado.getString("dose"),
	                        resultado.getDate("data_aplicacao"),
	                        resultado.getString("fabricante"),
	                        resultado.getString("lote"),
	                        resultado.getString("local_aplicacao"),
	                        resultado.getString("profissional_aplicador")));
	            }
	        }
	    }
	    
	    return lista;
	}

}
