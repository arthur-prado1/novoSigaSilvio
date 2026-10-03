package com.novosiga.novosiga.repository;

import com.novosiga.novosiga.dto.AlunoCurso;
import com.novosiga.novosiga.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    @Query("""
        select new com.novosiga.novosiga.dto.AlunoCursoDTO(
            a.nomeAluno,
            c.nomeCurso
            )
            from Aluno a
            join a.curso c
            order by c.nomeCurso, a.nomeAluno
    """)
    List<AlunoCurso> findAlunosComCursos();
}