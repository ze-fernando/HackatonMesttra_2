package com.mesttra.vacinas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mesttra.vacinas.config.ConexaoBanco;
import com.mesttra.vacinas.dto.DTOImunizacaoVacinaPaciente;


public class EstatisticaDAO {
	public static int qtdeVacinasAplicadasPorPaciente(int idPaciente ) throws SQLException{
		int estatistica = 0;
	    
		String sql = "SELECT COUNT(*) AS quantidadeVacinasAplicadas "
	               + "FROM imunizacoes "
	               + "WHERE id_paciente = ?";
	    
	    try (Connection conexao = ConexaoBanco.getConnection();
	         PreparedStatement comando = conexao.prepareStatement(sql)) {
	        
	        comando.setInt(1, idPaciente);
	        
	        try (ResultSet resultado = comando.executeQuery()) {
	            if (resultado.next()) {
	                estatistica = resultado.getInt("qtdeVacinasAplicadas");
	            }
	        }
	    }
	    
	    return estatistica;
	}
	
	public static int qtdeProximasImunizacoes(int idPaciente ) throws SQLException{
		int estatistica = 0;
	    
		String sql = "";
	    
	    try (Connection conexao = ConexaoBanco.getConnection();
	         PreparedStatement comando = conexao.prepareStatement(sql)) {
	        
	        comando.setInt(1, idPaciente);
	        
	        try (ResultSet resultado = comando.executeQuery()) {
	            if (resultado.next()) {
	                estatistica = resultado.getInt("qtdeVacinasParaProximoMes");
	            }
	        }
	    }
	    
	    return estatistica;
	}
	
	public static int consultarQtdeVacinasAtrasadasPorPaciente(int idPaciente) throws SQLException {
		int estatistica = 0;
		
		String sql = "";

	    try (Connection conexao = ConexaoBanco.getConnection();
	         PreparedStatement comando = conexao.prepareStatement(sql)) {

	        comando.setInt(1, idPaciente);

	        try (ResultSet resultado = comando.executeQuery()) {
	            if (resultado.next()) {
	                estatistica = resultado.getInt("qtdVacinasNaoAplicadas");
	            }
	        }
	    }
	    return estatistica;
	}
	
	public static int consultarVacinasAcimaDeIdade(int meses) throws SQLException {
		int estatistica = 0;
		
		String sql = "";

	    try (Connection conexao = ConexaoBanco.getConnection();
	         PreparedStatement comando = conexao.prepareStatement(sql)) {

	        comando.setInt(1, meses);

	        try (ResultSet resultado = comando.executeQuery()) {
	            if (resultado.next()) {
	                estatistica = resultado.getInt("qtdeVacinasAcimaDaIdade");
	            }
	        }
	    }
	    return estatistica;
	}
	
	public static int consultarVacinasNaoAplicaveis(int idPaciente) throws SQLException {
		int estatistica = 0;
		
		String sql = "";

	    try (Connection conexao = ConexaoBanco.getConnection();
	         PreparedStatement comando = conexao.prepareStatement(sql)) {

	        comando.setInt(1, idPaciente); 

	        try (ResultSet resultado = comando.executeQuery()) {
	            if (resultado.next()) {
					estatistica = resultado.getInt("qtdVacinasNaoAplicaveis");
	            }
	        }
	    }
	    return estatistica;
	}
}
