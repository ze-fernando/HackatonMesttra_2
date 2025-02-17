package com.mesttra.vacinas.controllers;

import static spark.Spark.*;

import com.mesttra.vacinas.services.EstatisticasService;

public classe EstatisticasService {

    public void getControllers(){

        //Qtde de vacinas aplicadas por paciente 
        get("/estatisticas/imunizacoes/paciente/:id"), EstatisticasService.readEstatisticaQtdeVacinas_By_Paciente();

        //Qtde de vacinas aplicáveis no próximo mês por paciente
        get("/estatisticas/proximas_imunizacoes/paciente/:id"), EstatisticasService.readEstatisticaQtde_Proximas_Imunizacoes();

        //Qtde de vacinas atrasadas 
        get("/estatisticas/imunizacoes_atrasadas/paciente/:id"), EstatisticasService.readEstatisticaQtde_Vacinas_Atrasadas();

        //Qtde de vacinas acima de uma determinda idade
        get("/estatisticas/imunizacoes/idade_maior/:meses"), EstatisticasService.readEstatisticaQtde_Idade_Acima();

        //Qtde de vacinas não aplicáveis
        get("/estatisticas/vacinas/nao_aplivacaveis/paciente/:id "), EstatisticasService.readEstatisticaQtde_Nao_Aplicaveis();

    }
}