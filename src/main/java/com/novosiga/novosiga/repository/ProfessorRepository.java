package com.novosiga.novosiga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.novosiga.novosiga.entity.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}
