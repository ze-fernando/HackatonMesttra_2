package com.mesttra.vacinas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mesttra.vacinas.config.ConexaoBanco;
import com.mesttra.vacinas.dto.DTOImunizacaoVacinaPaciente;


public class EstatisticaDAO {
	public static DTOImunizacaoVacinaPaciente qtdeVacinasAplicadasPorPaciente(int idPaciente ) throws SQLException{
		DTOImunizacaoVacinaPaciente estatistica = null;
	    
		String sql = "SELECT COUNT(*) AS quantidadeVacinasAplicadas "
	               + "FROM imunizacoes "
	               + "WHERE id_paciente = ?";
	    
	    try (Connection conexao = ConexaoBanco.getConnection();
	         PreparedStatement comando = conexao.prepareStatement(sql)) {
	        
	        comando.setInt(1, idPaciente);
	        
	        try (ResultSet resultado = comando.executeQuery()) {
	            if (resultado.next()) {
	                estatistica = new DTOImunizacaoVacinaPaciente(
	                        resultado.getInt("id"),
	                        resultado.getInt("qtdeVacinasAplicadas"));
	            }
	        }
	    }
	    
	    return estatistica;
	}
	
	public static DTOImunizacaoVacinaPaciente qtdeProximasImunizacoes(int idPaciente ) throws SQLException{
		DTOImunizacaoVacinaPaciente estatistica = null;
	    
		String sql = "";
	    
	    try (Connection conexao = ConexaoBanco.getConnection();
	         PreparedStatement comando = conexao.prepareStatement(sql)) {
	        
	        comando.setInt(1, idPaciente);
	        
	        try (ResultSet resultado = comando.executeQuery()) {
	            if (resultado.next()) {
	                estatistica = new DTOImunizacaoVacinaPaciente(
	                        resultado.getInt("id"),
	                        resultado.getInt("qtdeVacinasParaProximoMes"));
	            }
	        }
	    }
	    
	    return estatistica;
	}
	
	public static DTOImunizacaoVacinaPaciente consultarQtdeVacinasAtrasadasPorPaciente(int idPaciente) throws SQLException {
		DTOImunizacaoVacinaPaciente estatistica = null;
		
		String sql = "";

	    try (Connection conexao = ConexaoBanco.getConnection();
	         PreparedStatement comando = conexao.prepareStatement(sql)) {

	        comando.setInt(1, idPaciente);

	        try (ResultSet resultado = comando.executeQuery()) {
	            if (resultado.next()) {
	            	resultado.getInt("id");
	                resultado.getInt("qtdVacinasNaoAplicadas");
	            }
	        }
	    }
	    return estatistica;
	}
	
	public static DTOImunizacaoVacinaPaciente consultarVacinasAcimaDeIdade(int meses) throws SQLException {
		DTOImunizacaoVacinaPaciente estatistica = null;
		
		String sql = "";

	    try (Connection conexao = ConexaoBanco.getConnection();
	         PreparedStatement comando = conexao.prepareStatement(sql)) {

	        comando.setInt(1, meses);

	        try (ResultSet resultado = comando.executeQuery()) {
	            if (resultado.next()) {
	            	resultado.getInt("id");
	                resultado.getInt("qtdeVacinasAcimaDaIdade");
	            }
	        }
	    }
	    return estatistica;
	}
	
	public static DTOImunizacaoVacinaPaciente consultarVacinasNaoAplicaveis(int idPaciente) throws SQLException {
		DTOImunizacaoVacinaPaciente estatistica = null;
		
		String sql = "";

	    try (Connection conexao = ConexaoBanco.getConnection();
	         PreparedStatement comando = conexao.prepareStatement(sql)) {

	        comando.setInt(1, idPaciente); 

	        try (ResultSet resultado = comando.executeQuery()) {
	            if (resultado.next()) {
	            	 resultado.getInt("id");
	                 resultado.getInt("qtdVacinasNaoAplicaveis");
	            }
	        }
	    }
	    return estatistica;
	}
}
