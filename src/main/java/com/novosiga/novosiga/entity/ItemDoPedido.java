package com.novosiga.novosiga.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDoPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idItem;

    private Integer quantidade;
    private Double preco;
    private Double subtotal;

    // Relacionamento com pedido
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idPedido_fk")
    private Pedido pedido;

    // Relacionamento com produto
    @ManyToOne
    @JoinColumn(name = "idProduto_fk")
    private Produto produto;

    // Método para calcular subtotal
    public Double calcularSubTotal() {
        if (quantidade == null || preco == null) {
            return 0.0;
        }
        return quantidade * preco;
    }

    // Método para atualizar o subtotal
    public void atualizarSubtotal() {
        this.subtotal = calcularSubTotal();
    }
}