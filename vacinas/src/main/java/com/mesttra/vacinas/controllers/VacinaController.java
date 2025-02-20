package com.mesttra.vacinas.controllers;

import static spark.Spark.*;

import com.mesttra.vacinas.services.VacinaService;

public class VacinaController {

    public static void getControllers(){

        //Consultar todas as vacinas
        get("/vacinas/consultar", VacinaService.consultarTodasVacinas());

        //Consultar todas as vacinas para uma determinada faixa etária
        get(" /vacinas/consultar/faixa_etaria/:faixa", VacinaService.consultarVacinasPorFaixaEtaria());

        //Consultar todas as vacinas recomendadas acima de uma idade
        get("/vacinas/consultar/idade_maior/:meses", VacinaService.consultarVacinasPorIdadeMaior());

        //Consultar todas as vacinas não aplicáveis para um determinado paciente
        get("/vacinas/consultar/nao_aplivacaveis/paciente/:id", VacinaService.consultarVacinasNaoAplicaveis());
        
    }

}


