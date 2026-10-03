package com.novosiga.novosiga.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAluno;
    
    @Column(nullable = false, length = 50)
    private String nomeAluno;

    @Column(nullable = false, length = 40)
    private String enderecoAluno;

    @Column(nullable = false, length = 40)
    private String bairroAluno;

    @Column(nullable = false, length = 30)
    private String cidadeAluno;

    @Column(nullable = false, length = 2)
    private String estadoAluno;

    @Column(nullable = false, length = 8)
    private String cepAluno;

    @Column(nullable = false, length = 11)
    private String telefoneAluno;

    @Column(nullable = false, length = 11)
    private String cpfAluno;

    @Lob
    private byte[] fotoAluno;

    @Column(length = 20)
    private String tipoFoto;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;
}
