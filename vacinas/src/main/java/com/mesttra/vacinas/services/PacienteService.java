package com.mesttra.vacinas.services;

import com.mesttra.vacinas.models.Paciente;

public class PacienteService {
    
    public static Paciente createPaciente(Request req){
        var newPaciente = new Paciente(
            req.queryParams("nome"),
            req.queryParams("cpf"),
            req.queryParams("sexo"),
            req.queryParams("nascimento")
        );

        // Logica pra inserir no banco de dados

        return newPaciente;
    }
    public static List<Paciente> readPaciente(Request req) {
        List<Paciente> pacientes = new ArrayList<>();
        // pacientes = Lógica para pegar todos os pacientes
    
        return pacientes;
    }
    
    public static Paciente readPacienteById(Request req) {
        Integer id = Integer.parseInt(req.queryParams("id"));
        Paciente paciente = null;
        // paciente = Lógica para pegar o paciente via id
    
        return paciente;
    }
    
    public static Paciente updatePaciente(Request req) {
        Paciente pacienteDb = null;
        Integer id = Integer.parseInt(req.params(":id"));
        // pacienteDb = Lógica para pegar o paciente via id
    
        String nome = req.queryParams("nome");
        String cpf = req.queryParams("cpf");
        String sexo = req.queryParams("sexo");
        String nascimento = req.queryParams("nascimento");
    
        pacienteDb.setName(nome);
        pacienteDb.setCpf(cpf);
        pacienteDb.setSexo(sexo);
        pacienteDb.setData(nascimento);
    
        // Lógica para salvar o paciente no db
    
        return pacienteDb;
    }
    
    public static boolean deletePaciente(Request req) {
        Paciente paciente = null;
        Integer id = Integer.parseInt(req.params(":id"));
        // Lógica paciente = para pegar o paciente via id
    
        if (paciente == null) {
            return false;
        }
    
        // Lógica para deletar o paciente
    
        return true;
    }
    
}