package com.mesttra.vacinas.services;

import com.mesttra.vacinas.models.Paciente;

public class PacienteService {
    
    public Paciente createPaciente(PacienteDto paciente){
        var newPaciente = new Paciente(
            paciente.getName(),
            paciente.getCpf(),
            paciente.getSexo(),
            paciente.getNascimento()
        );

        // Logica pra inserir no banco de dados

        return newPaciente;
    }

    public List<Paciente> readPaciente(){
        List<Paciente> pacientes;
        //pacientes = Logica para pegar todos os pacientes;

        return pacientes;
    }

    public Paciente readPacienteById(Integer id){
        Paciente paciente;
        //paciente = Logica para pegar um paciente via id;

        return paciente;
    }

    public Paciente updatePaciente(Integer id, PacienteDto paciente){
        Paciente pacienteDb;
        //pacienteDb = Logica para pegar um paciente via id;

        pacienteDb.setName(paciente.getName());
        pacienteDb.setCpf(paciente.getCpf());
        pacienteDb.setSexo(paciente.getSexo());
        pacienteDb.setData(paciente.getData());

        // Logica para salvar o paciente no db;

        return pacienteDb;

    }

    public boolean deletePaciente(Integer id){
        Paciente paciente;
        //paciente = Logica para pegar um paciente via id;

        if(paciente == null){
            return false;
        }

        // Logica para deletar paciente

        return true;
    }
}