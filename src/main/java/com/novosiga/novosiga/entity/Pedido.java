package com.novosiga.novosiga.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPedido;

    @Column(nullable = false)
    private LocalDate dataPedido;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPedido = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "idAluno_fk")
    private Aluno aluno;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemDoPedido> itens;

    public void atualizarTotal() {
        if (itens == null || itens.isEmpty()) {
            this.totalPedido = BigDecimal.ZERO;
            return;
        }
        double soma = 0.0;
        for (ItemDoPedido item : itens) {
            if (item.getSubtotal() != null) {
                soma += item.getSubtotal();
            }
        }
        this.totalPedido = BigDecimal.valueOf(soma);
    }
}