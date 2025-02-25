package com.mesttra.vacinas.services;

import spark.Request;
import spark.Response;
import spark.Route;


import com.mesttra.vacinas.models.Paciente;

import java.util.List;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mesttra.vacinas.dao.PacienteDAO;
import com.mesttra.vacinas.models.Paciente.Sexo;

public class PacienteService {

     private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    public static Route createPaciente(){
        return new Route() {
            @Override
            public Object handle(Request req, Response res){       
                try {
                    // Convertendo os parâmetros da requisição
                    String nome = req.queryParams("nome");
                    String cpf = req.queryParams("cpf");
                    String sexoParam = req.queryParams("sexo");
                    String nascimentoParam = req.queryParams("nascimento");

                    // Validando os parâmetros
                    if (nome == null || cpf == null || sexoParam == null || nascimentoParam == null) {
                        res.status(400);
                        return "{\"error\": \"Todos os campos são obrigatórios.\"}";
                    }

                    Sexo sexo;
                    try {
                        sexo = Sexo.valueOf(sexoParam.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        res.status(400);
                        return "{\"error\": \"Sexo inválido. Use 'M' ou 'F'.\"}";
                    }

                    Date dataNascimento = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(nascimentoParam).getTime());

                    // Criando o novo paciente
                    Paciente newPaciente = new Paciente(0, nome, cpf, sexo, dataNascimento);
                    
                    // Adicionando o paciente ao banco de dados
                    PacienteDAO.adicionarPaciente(newPaciente);
                    res.status(201);

                    return "{\"message\": \"Paciente " + newPaciente.getNome() + " inserido com sucesso.\"}" ;
                } catch (Exception e) {
                    res.status(500);
                    return "{\"error\": \"" + e.getMessage() + "\"}" ;
                }
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

                    return gson.toJson(pacientes);
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
                    String idParam = req.queryParams("id");

                    if (idParam == null) {
                        res.status(400);
                        return "{\"error\": \"ID é obrigatório.\"}";
                    }

                    int id = Integer.parseInt(idParam);
                    Paciente paciente = PacienteDAO.consultarPacientePorId(id);

                    if (paciente == null) {
                        res.status(404);
                        return "{\"message\": \"Paciente não encontrado.\"}";
                    }

                    res.status(200);
                    return gson.toJson(paciente);
                } catch (NumberFormatException e) {
                    res.status(400);
                    return "{\"error\": \"ID inválido.\"}";
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
                try {
                    String idParam = req.params(":id");
    
                    if (idParam == null) {
                        res.status(400);
                        return "{\"error\": \"ID é obrigatório.\"}";
                    }
    
                    int id = Integer.parseInt(idParam);
                    Paciente pacienteDb = PacienteDAO.consultarPacientePorId(id);
    
                    if (pacienteDb == null) {
                        res.status(404);
                        return "{\"message\": \"Paciente não encontrado.\"}";
                    }
    
                    // Atualizando os dados do paciente
                    String nome = req.queryParams("nome");
                    String cpf = req.queryParams("cpf");
                    String sexoParam = req.queryParams("sexo");
                    String nascimentoParam = req.queryParams("nascimento");
    
                    if (nome != null && !nome.isEmpty()) {
                        pacienteDb.setNome(nome);
                    }
                    if (cpf != null && !cpf.isEmpty()) {
                        pacienteDb.setCpf(cpf);
                    }
                    if (sexoParam != null && !sexoParam.isEmpty()) {
                        try {
                            Sexo sexo = Sexo.valueOf(sexoParam.toUpperCase());
                            pacienteDb.setSexo(sexo);
                        } catch (IllegalArgumentException e) {
                            res.status(400);
                            return "{\"error\": \"Sexo inválido. Use 'M' ou 'F'.\"}";
                        }
                    }
                    if (nascimentoParam != null && !nascimentoParam.isEmpty()) {
                        try {
                            java.sql.Date dataNascimento = java.sql.Date.valueOf(nascimentoParam);
                            pacienteDb.setDataNascimento(dataNascimento);
                        } catch (IllegalArgumentException e) {
                            res.status(400);
                            return "{\"error\": \"Data de nascimento inválida. Use o formato 'yyyy-MM-dd'.\"}";
                        }
                    }
    
                    PacienteDAO.alterarPaciente(pacienteDb);
                    res.status(200);
                    return "{\"message\": \"Paciente atualizado com sucesso.\"}";
                } catch (NumberFormatException e) {
                    res.status(400);
                    return "{\"error\": \"ID inválido.\"}";
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