package com.mesttra.vacinas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mesttra.vacinas.config.ConexaoBanco;
import com.mesttra.vacinas.models.Paciente;
import com.mesttra.vacinas.models.Paciente.Sexo;

public class PacienteDAO {
	public static void adicionarPaciente(Paciente paciente) throws SQLException{
        String sql = "INSERT INTO pacientes (nome, cpf, sexo, data_nascimento,) VALUES (?, ?, ?, ?)";
        
        try (Connection conexao = ConexaoBanco.getConnection();
             PreparedStatement comando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            comando.setString(1, paciente.getNome());
            comando.setString(2, paciente.getCpf());
            comando.setString(3, paciente.getSexo().name()); 
            comando.setDate(4, new java.sql.Date(paciente.getDataNascimento().getTime()));
            
            comando.executeUpdate();
            
            try (ResultSet resultado = comando.getGeneratedKeys()) {
                if (resultado.next()) {
                    int idGerado = resultado.getInt(1);
                    paciente.setId(idGerado);
                    
                    System.out.println("Paciente inserido com ID: " + idGerado);
                }
            }
        }
    }	
	public static void alterarPaciente(Paciente paciente) throws SQLException {
    	String sql = "UPDATE pacientes SET nome = ?, cpf = ?, sexo = ?, data_nascimento = ? WHERE id = ?";
    	
    	try (Connection conexao = ConexaoBanco.getConnection();
			 PreparedStatement comando = conexao.prepareStatement(sql)){
    		
    		comando.setString(1, paciente.getNome());
            comando.setString(2, paciente.getCpf());
            comando.setString(3, paciente.getSexo().name());         
            comando.setInt(4, paciente.getId()); 
            
            comando.executeUpdate();
        }
    }
	
	public static void excluirPaciente(int id) throws SQLException {
    	String sql = "DELETE FROM pacientes WHERE id = ?";
    	
    	try (Connection conexao = ConexaoBanco.getConnection();
			 PreparedStatement comando = conexao.prepareStatement(sql)){
			
    		comando.setInt(1, id);
    		
    		comando.executeUpdate();
		}
    }
	
	public static List<Paciente> consultarTodosPacientes() throws SQLException{
    	List<Paciente> lista = new ArrayList<>();
    	
    	String sql = "SELECT * FROM pacientes";
    	
    	try (Connection conexao = ConexaoBanco.getConnection();
    		 Statement comando = conexao.createStatement();
			 ResultSet resultado = comando.executeQuery(sql)) {
    		
    		while (resultado.next()) {
    			
    			lista.add(new Paciente(	
					resultado.getInt("id"),
					resultado.getString("nome"),
					resultado.getString("cpf"),
					Sexo.valueOf(resultado.getString("sexo")),
					resultado.getDate("data_nascimento")));   								   									
    		}    		    			    					
		}
    	return lista;
    }
	
	public static Paciente consultarPacientePorId(int id) throws SQLException {
    	String sql = "SELECT * FROM pacientes WHERE Id = ?";
    	
    	Paciente paciente = null;
    	
    	try (Connection conexao = ConexaoBanco.getConnection();
			 PreparedStatement comando = conexao.prepareStatement(sql)) {
    		
    		comando.setInt(1, id);
    		
    		try(ResultSet resultado = comando.executeQuery()) {
    			if (resultado.next()) {
                    paciente = new Paciente(	
                    		resultado.getInt("id"),
        					resultado.getString("nome"),
        					resultado.getString("cpf"),
        					Sexo.valueOf(resultado.getString("sexo")),
        					resultado.getDate("data_nascimento"));		                            
                }
            }
    	}
    	return paciente;
    }
}
