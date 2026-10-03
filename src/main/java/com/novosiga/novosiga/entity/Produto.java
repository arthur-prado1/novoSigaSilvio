package com.novosiga.novosiga.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idProduto;

    @Column(nullable = false, length = 100)
    private String descricao;

    @Column(nullable = false, length = 50)
    private String marca;

    @Column(nullable = false, length = 50)
    private String modelo;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Lob
    private byte[] imagem;

    @Column(length = 20)
    private String tipoImagem;

    @OneToMany(mappedBy = "produto")
    private List<ItemDoPedido> itens;
}