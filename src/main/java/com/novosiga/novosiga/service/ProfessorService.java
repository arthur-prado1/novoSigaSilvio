package com.novosiga.novosiga.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novosiga.novosiga.entity.Professor;
import com.novosiga.novosiga.repository.ProfessorRepository;

@Service
public class ProfessorService {
    
    @Autowired
    private ProfessorRepository professorRepository;
    
    public Professor save(Professor professor) {
        return professorRepository.save(professor);
    }

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    public void deleteById(Integer id) {
        professorRepository.deleteById(id);
    }

    public Professor findByIdProfessor(Integer id) {
        return professorRepository.findById(id).orElse(null);
    }
}
