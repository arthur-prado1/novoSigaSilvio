package com.novosiga.novosiga.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novosiga.novosiga.entity.Disciplina;
import com.novosiga.novosiga.repository.DisciplinaRepository;

@Service
public class DisciplinaService {
    
    @Autowired
    private DisciplinaRepository disciplinaRepository;
    
    public Disciplina save(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    public List<Disciplina> findAll() {
        return disciplinaRepository.findAll();
    }

    public void deleteById(Integer id) {
        disciplinaRepository.deleteById(id);
    }

    public Disciplina findByIdDisciplina(Integer id) {
        return disciplinaRepository.findById(id).orElse(null);
    }
}
