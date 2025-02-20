package com.mesttra.vacinas.services;

import spark.Request;
import spark.Response;
import spark.Route;


import com.mesttra.vacinas.models.Paciente;
import com.mesttra.vacinas.dao.PacienteDAO;
import com.mesttra.vacinas.dao.EstatisticaDAO;

public class EstatisticasService {

    public static Route readQtdeVacinasByPaciente(){
        return new Route() {
            @Override
            public Object handle(Request req, Response res){
                int idPaciente = Integer.parseInt(req.queryParams("id"));

                try {
                    int qtdVacinas = EstatisticaDAO
                    .qtdeVacinasAplicadasPorPaciente(idPaciente);
                    res.status(200);
                    return "{\"message\": \"Vacinas " + qtdVacinas + ".\"}" ;
                    
                } catch (Exception e) {
                    res.status(500);
                    return "{\"error\": \"" + e.getMessage() + "\"}";
                }
            }
        };
    }

    public static Route readQtdeProximasImunizacoes(){
        return new Route() {
            @Override
            public Object handle(Request req, Response res){
                int idPaciente = Integer.parseInt(req.queryParams("id"));

                try {
                    int qtdVacinasProxMes = EstatisticaDAO
                    .qtdeProximasImunizacoes(idPaciente);
                    res.status(200);
                    return "{\"message\": \"Vacinas " + qtdVacinasProxMes + ".\"}" ;
                    
                } catch (Exception e) {
                    res.status(500);
                    return "{\"error\": \"" + e.getMessage() + "\"}";
                }
            }
        };
    }

    public static Route readQtdeVacinasAtrasadas(){
        return new Route() {
            @Override
            public Object handle(Request req, Response res){
                int idPaciente = Integer.parseInt(req.queryParams("id"));

                try {
                    int qtdVacinasAtrasadas = EstatisticaDAO
                    .consultarQtdeVacinasAtrasadasPorPaciente(idPaciente);
                    res.status(200);
                    return "{\"message\": \"Vacinas Atrasadas" + qtdVacinasAtrasadas + ".\"}" ;
                    
                } catch (Exception e) {
                    res.status(500);
                    return "{\"error\": \"" + e.getMessage() + "\"}";
                }
            }
        };
    }

    public static Route readQtdeIdadeAcima(){
        return new Route() {
            @Override
            public Object handle(Request req, Response res){
                int idPaciente = Integer.parseInt(req.queryParams("id"));
                int month = Integer.parseInt(req.queryParams("meses"));

                try {
                    int qtdVacinasAcimaIdade = EstatisticaDAO
                    .consultarVacinasAcimaDeIdade(idPaciente, month);

                    res.status(200);
                    return "{\"message\": \"Vacinas " + qtdVacinasAcimaIdade + ".\"}" ;
                    
                } catch (Exception e) {
                    res.status(500);
                    return "{\"error\": \"" + e.getMessage() + "\"}";
                }
            }
        };
    }

    public static Route readQtdeNaoAplicaveis(){
        return new Route() {
            @Override
            public Object handle(Request req, Response res){
                int idPaciente = Integer.parseInt(req.queryParams("id"));

                try {
                    int qtdVacinasNotApplicable = EstatisticaDAO
                    .consultarVacinasNaoAplicaveis(idPaciente);
                    res.status(200);
                    return "{\"message\": \"Vacinas " + qtdVacinasNotApplicable + ".\"}" ;
                    
                } catch (Exception e) {
                    res.status(500);
                    return "{\"error\": \"" + e.getMessage() + "\"}";
                }
            }
        };
    }
}