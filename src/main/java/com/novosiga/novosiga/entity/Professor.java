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
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idProfessor;
    
    @Column(nullable = false, length = 100)
    private String nomeProfessor;

    @Column(nullable = false, length = 11)
    private String cpfProfessor;

    @Column(nullable = false, length = 20)
    private String rgProfessor;

    @Column(nullable = false, length = 15)
    private String telProfessor;

    @Column(nullable = false, length = 150)
    private String endProfessor;

    @Column(nullable = false, length = 100)
    private String graduacaoProfessor;
}
