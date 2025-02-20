package com.mesttra.vacinas.services;

import spark.Request;
import spark.Response;
import spark.Route;


import com.mesttra.vacinas.models.Paciente;
import com.mesttra.vacinas.dao.PacienteDAO;

public class PacienteService {
    
    public static Route createPaciente(){
        return new Route() {
            @Override
            public Object handle(Request req, Response res){       
                    var newPaciente = new Paciente(
                        req.queryParams("nome"),
                        req.queryParams("cpf"),
                        req.queryParams("sexo"),
                        req.queryParams("nascimento")
                );
                try {
                    
                    PacienteDAO.adicionarPaciente(newPaciente);
                    res.status(201);

                    return "{\"message\": \"Paciente " + newPaciente + " com sucesso.\"}" ;
                } catch (Exception e) {
                    res.status(500);
                    return "{\"error\": \"" + e.getMessage() + "\"}" ;
                };
            };
        };
    }

    public static Route readPaciente() {
        return new Route() {
            @Override
            public Object handle(Request req, Response res) {
                try {
                    List<Paciente> pacientes = PacienteDAO.consultarTodosPacientes();
                    res.status(200);
    
                    return "{\"message\": \"Pacientes " + pacientes + " encontrados com sucesso.\"}";
                } catch (Exception e) {
                    res.status(500);
                    return "{\"error\": \"" + e.getMessage() + "\"}";
                }
            }
        };
    }
    
    public static Route readPacienteById() {
        return new Route() {
            @Override
            public Object handle(Request req, Response res) {
                try {
                    int id = Integer.parseInt(req.queryParams("id"));
                    Paciente paciente = PacienteDAO.consultarPacientePorId(id);
                    res.status(200);
    
                    return paciente;
                } catch (Exception e) {
                    res.status(500);
                    return "{\"error\": \"" + e.getMessage() + "\"}";
                }
            }
        };
    }
    
    public static Route updatePaciente() {
        return new Route() {
            @Override
            public Object handle(Request req, Response res) {
                int id = Integer.parseInt(req.params(":id"));
                try {
                    Paciente pacienteDb = PacienteDAO.consultarPacientePorId(id);

                    if(pacienteDb == null){
                        res.status(404);
                        return "{\"message\": \"Paciente não encontrado.\"}";
                    }

                    pacienteDb.setNome(req.queryParams("nome"));
                    pacienteDb.setCpf(req.queryParams("cpf"));
                    pacienteDb.setDataNascimento(req.queryParams("sexo"));
                    pacienteDb.setSexo(req.queryParams("nascimento"));
    
                    PacienteDAO.alterarPaciente(pacienteDb);
                    res.status(200);
    
                    return "{\"message\": \"Paciente atualizado com sucesso.\"}";
                } catch (Exception e) {
                    res.status(500);
                    return "{\"error\": \"" + e.getMessage() + "\"}";
                }
            }
        };
    }
    
    public static Route deletePaciente() {
        return new Route() {
            @Override
            public Object handle(Request req, Response res){ 
                int id = Integer.parseInt(req.params(":id"));
                try {
    
                    PacienteDAO.excluirPaciente(id);
                    res.status(200);
    
                    return "{\"message\": \"Paciente excluído com sucesso.\"}";
                } catch (Exception e) {
                    res.status(500);
                    return "{\"error\": \"" + e.getMessage() + "\"}";
                }
            }
        };
    }
}