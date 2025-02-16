package com.mesttra.vacinas.services;

import com.mesttra.vacinas.models.Paciente;
import com.mesttra.vacinas.dao.PacienteDAO;

public class PacienteService {
    
    public static Paciente createPaciente(Request req){
        var newPaciente = new Paciente(
            req.queryParams("nome"),
            req.queryParams("cpf"),
            req.queryParams("sexo"),
            req.queryParams("nascimento")
        );

        PacienteDAO.adicionarPaciente(newPaciente);

        return newPaciente;
    }
    public static List<Paciente> readPaciente(Request req) {
        List<Paciente> pacientes =  PacienteDAO.consultarTodosPacientes();
    
        return pacientes;
    }
    
    public static Paciente readPacienteById(Request req) {
        int id = int.parseInt(req.queryParams("id"));
        Paciente paciente = PacienteDAO.consultarPacientePorId(id);
           
        return paciente;
    }
    
    public static Paciente updatePaciente(Request req) {
        int id = int.parseInt(req.params(":id"));
        Paciente pacienteDb = PacienteDAO.consultarPacientePorId(id);
        
        PacienteDAO.alterarPaciente(pacienteDb);
    
        return pacienteDb;
    }
    
    public static void deletePaciente(Request req) {
        int id = int.parseInt(req.params(":id"));
        
        PacienteDAO.excluirPaciente(id);
    }
    
}