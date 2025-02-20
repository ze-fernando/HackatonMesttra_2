package com.mesttra.vacinas.services;

import spark.Request;
import spark.Response;
import spark.Route;

import com.mesttra.vacinas.models.Vacina;
import com.mesttra.vacinas.dao.VacinaDAO;
import com.mesttra.vacinas.dto.DTOVacinaDose;
import com.mesttra.vacinas.models.enums.PublicoAlvo;

import java.sql.SQLException;
import java.util.List;

public class VacinaService {

    public static Route consultarTodasVacinas() {
        return new Route() {
            @Override
            public Object handle(Request req, Response res) {
                try {
                    List<Vacina> vacinas = VacinaDAO.consultarTodasVacinas();
                    res.status(200);
                    
                    return "{\"data\": " + vacinas.toString() + "}";
                } catch (Exception e) {
                    res.status(500);
                    return "{\"error\": \"" + e.getMessage() + "\"}" ;
                };
            }
        };
    }

    public static Route consultarVacinasPorFaixaEtaria() {
        return new Route() {
            @Override
            public Object handle(Request req, Response res) {
                try {
                    PublicoAlvo publicoAlvo = PublicoAlvo.valueOf(req.queryParams("publico_alvo"));
                    List<DTOVacinaDose> vacinas = VacinaDAO.consultarTodasVacinasPorFaixaEtaria(publicoAlvo);
                    res.status(200);
                    return "{\"data\": " + vacinas.toString() + "}";
                } catch (Exception e) {
                    res.status(500);
                    return "{\"error\": \"" + e.getMessage() + "\"}" ;
                };
            }
        };
    }

    public static Route consultarVacinasPorIdadeMaior() {
        return new Route() {
            @Override
            public Object handle(Request req, Response res) {
                try {
                    int meses = Integer.parseInt(req.queryParams("meses"));
                    List<DTOVacinaDose> vacinas = VacinaDAO.consultarTodasVacinasPorIdadeMaior(meses);
                    res.status(200);
                    return "{\"data\": " + vacinas.toString() + "}";
                } catch (Exception e) {
                    res.status(500);
                    return "{\"error\": \"" + e.getMessage() + "\"}" ;
                };
            }
        };
    }

    public static Route consultarVacinasNaoAplicaveis() {
        return new Route() {
            @Override
            public Object handle(Request req, Response res) {
                try {
                    int idPaciente = Integer.parseInt(req.queryParams("id_paciente"));
                    List<DTOVacinaDose> vacinas = VacinaDAO.consultarTodasVacinasNaoAplicaveis(idPaciente);
                    res.status(200);
                    return "{\"data\": " + vacinas.toString() + "}";
                } catch (Exception e) {
                    res.status(500);
                    return "{\"error\": \"" + e.getMessage() + "\"}" ;
                };
            }
        };
    }
}
