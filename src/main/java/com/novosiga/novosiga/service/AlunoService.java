package com.novosiga.novosiga.service;

import com.novosiga.novosiga.entity.Aluno;
import com.novosiga.novosiga.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {
    //Injeção de dependência do repositório de alunos
    @Autowired
    private AlunoRepository alunoRepository;

    //metodo para salvar um aluno
    public Aluno save(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    //metodo para listar todos os alunos
    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    // Método para excluir um aluno pelo ID
    public void deleteById(Integer id) {
        alunoRepository.deleteById(id);
    }

    // Método para buscar o aluno pelo ID
    public Aluno findByIdAluno(Integer id) {
        return alunoRepository.findById(id).orElse(null);
    }

    public List<AlunoCursoDTO> listarAlunosPorCurso
}
