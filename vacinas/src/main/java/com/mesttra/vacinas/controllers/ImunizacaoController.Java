package com.mesttra.vacinas.controllers;

import static spark.Spark.*;

import com.mesttra.vacinas.services.ImunizacaoService;

public class ImunizacaoController {

    public void getControllers() {

        // Criar uma nova imunização
        post("/imunizacao/inserir", ImunizacaoService.createImunizacao());

        // Alterar uma imunização ja existente buscando pelo ID
        put("/imunizacao/alterar/:id", ImunizacaoService.updateImunizacao());

        // Consultar todas as imunizações cadastradas
        get("/imunizacao/consultar", ImunizacaoService.readImunizacoes());

        // Consultar uma imunização específica por ID
        get("/imunizacao/consultar/:id", ImunizacaoService.readImunizacaoById());

        // Consultar imunizações de um paciente específico
        get("/imunizacao/consultar/paciente/:id", ImunizacaoService.readImunizacoesByPaciente());

        // Consultar imunizações por ID do paciente e intervalo de datas
        get("/imunizacao/consultar/paciente/:id/aplicacao/:dt_ini/:dt_fim", ImunizacaoService.readImunizacoesByPacienteAndDate());

        // Excluir uma imunização em  específico
        delete("/imunizacao/excluir/:id", ImunizacaoService.deleteImunizacaoById());

        // Excluir todas as imunizações de um paciente específico pelo ID
        delete("/imunizacao/excluir/paciente/:id", ImunizacaoService.deleteImunizacoesByPaciente());
    }
}
