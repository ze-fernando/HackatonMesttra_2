package com.mesttra.vacinas.controllers;

import static spark.Spark.*;

import com.mesttra.vacinas.services.PacienteService;


public class PacienteController {

    public void getControllers(){
        get("/paciente/consultar/", PacienteService.readPaciente());
        get("/paciente/consultar/:id", PacienteService.readPacienteById());
    }    
}