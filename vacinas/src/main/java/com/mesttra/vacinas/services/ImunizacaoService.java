package com.mesttra.vacinas.services;

import com.mesttra.vacinas.dao.ImunizacoesDAO;
import com.mesttra.vacinas.models.Imunizacoes;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class ImunizacaoService {

    public static Route createImunizacao() {
        return new Route() {
            @Override
            public Object handle(Request req, Response res) {
                try {
                    Imunizacoes newImunizacao = new Imunizacoes(
                            req.queryParams("paciente_id"),
                            req.queryParams("vacina_id"),
                            req.queryParams("data_aplicacao"));

                    ImunizacoesDAO.adicionarImunizacao(newImunizacao);
                    res.status(201);
                    return "{\"message\": \" Imunização criada com sucesso \"}";
                } catch (Exception e) {
                    res.status(500);
                    return "{\"error\": \"" + e.getMessage() + "\"}";
                }
            }
        };
    }

    public static Route readImunizacoes() {
        return new Route() {
            @Override
            public Object handle(Request req, Response res) {
                try {
                    List<Imunizacoes> imunizacoes = ImunizacoesDAO.consultarTodasImunizacoes();
                    res.status(200);
                    return "{\"message\": \"" + imunizacoes.toString() + "\"}";
                } catch (Exception e) {
                    res.status(500);
                    return "{\"error\": \"" + e.getMessage() + "\"}";
                }
            }
        };
    }

    public static Route readImunizacaoById() {
        return new Route() {
            @Override
            public Object handle(Request req, Response res) {
                try {
                    int id = Integer.parseInt(req.params(":id"));
                    Imunizacoes imunizacao = ImunizacoesDAO.consultarImunizacaoPorIdImunizacao(id);

                    if (imunizacao == null) {
                        res.status(404);
                        return "{\"message\": \" Imunização não encontrada \"}";
                    }
                    res.staus(200);
                    return "{\"message\": \"" + imunizacao.toString() + "\"}";
                } catch (Exception e) {
                    res.status(500);
                    return "{\"error\": \"" + e.getMessage() + "\"}";
                }
            }
        };
    }

    public static Route readImunizacoesByPaciente() {
        return new Route() {
            @Override
            public Object handle(Request req, Response res) {
                try {
                    int pacienteId = Integer.parseInt(req.params(":id"));
                    List<Imunizacoes> imunizacoes = ImunizacoesDAO.consultarImunizacoesPorIdPaciente(pacienteId);
                    res.status(200);
                    return "{\"message\": \"" + imunizacoes.toString() + "\"}";
                } catch (Exception e) {
                    res.status(500);
                    return "{\"error\": \"" + e.getMessage() + "\"}";
                }
            }
        };
    }

    public static Route readImunizacoesByPacienteIdAndPeriodo() {
        return new Route() {
            @Override
            public Object handle(Request req, Response res) {
                try {
                    int pacienteId = Integer.parseInt(req.params(":id"));
                    String dataInicio = req.queryParams("data_inicio");
                    String dataFim = req.queryParams("data_fim");

                    List<Imunizacoes> imunizacoes = ImunizacoesDAO
                            .consultarImunizacoesPorIdPacienteEPeriodo(pacienteId, dataInicio, dataFim);
                    res.status(200);
                    return "{\"message\": \"" + imunizacoes.toString() + "\"}";
                } catch (Exception e) {
                    res.status(500);
                    return "{\"error\": \"" + e.getMessage() + "\"}";
                }
            }
        };
    }

    public static Route deleteImunizacaoById() {
        return new Route() {
            @Override
            public Object handle(Request req, Response res) {
                try {
                    int id = Integer.parseInt(req.params(":id"));
                    ImunizacoesDAO.excluirImunizacao(id);
                    res.status(204);
                } catch (Exception e) {
                    res.status(500);
                    return "{\"error\": \"" + e.getMessage() + "\"}";
                }
            }
        };
    }

    public static Route deleteImunizacoesByPaciente() {
        return new Route() {
            @Override
            public Object handle(Request req, Response res) {
                try {
                    int pacienteId = Integer.parseInt(req.params(":id"));
                    ImunizacoesDAO.excluirTodasImunizacoesDoPaciente(pacienteId);
                    res.status(204);
                } catch (Exception e) {
                    res.status(500);
                    return "{\"error\": \"" + e.getMessage() + "\"}";
                }
            }
        };
    }

    public static Route updateImunizacao() {
        return new Route() {
            @Override
            public Object handle(Request req, Response res) {
                try {
                    int id = Integer.parseInt(req.params(":id"));
                    Imunizacoes imunizacaoDb = ImunizacoesDAO.consultarImunizacaoPorIdImunizacao(id);

                    if (imunizacaoDb == null) {
                        res.status(404);
                        return "Imunização não encontrada.";
                    }

                    imunizacaoDb.setPacienteId(req.queryParams("paciente_id"));
                    imunizacaoDb.setVacinaId(req.queryParams("vacina_id"));
                    imunizacaoDb.setDataAplicacao(req.queryParams("data_aplicacao"));

                    ImunizacoesDAO.alterarImunizacao(imunizacaoDb);
                    res.status(200);
                    return "{\"message\": \" Imunização atualizada com sucesso \"}";
                } catch (Exception e) {
                    res.status(500);
                    return "{\"error\": \"" + e.getMessage() + "\"}";
                }
            }
        };
    }
}
