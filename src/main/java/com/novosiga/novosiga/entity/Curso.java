package com.novosiga.novosiga.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCurso;
    
    @Column(nullable = false, length = 100)
    private String nomeCurso;

    @Column(nullable = false, length = 255)
    private String descricaoCurso;

    @Column(nullable = false)
    private Integer cargaHorariaCurso;
}
