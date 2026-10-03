package com.novosiga.novosiga.service;

import com.novosiga.novosiga.entity.ItemDoPedido;
import com.novosiga.novosiga.entity.Pedido;
import com.novosiga.novosiga.entity.Produto;
import com.novosiga.novosiga.repository.PedidoRepository;
import com.novosiga.novosiga.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Pedido salvarPedido(Pedido pedido) {
        pedido.setDataPedido(LocalDate.now());
        if (pedido.getItens() != null) {
            for (ItemDoPedido item : pedido.getItens()) {
                if (item.getProduto() != null && item.getProduto().getIdProduto() != null) {
                    Produto produto = produtoRepository.findById(item.getProduto().getIdProduto())
                            .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + item.getProduto().getIdProduto()));
                    item.setPreco(produto.getValor().doubleValue());
                    item.atualizarSubtotal();
                    item.setPedido(pedido);
                }
            }
            pedido.atualizarTotal();
        }
        return pedidoRepository.save(pedido);
    }

    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public void deleteById(Integer id) {
        pedidoRepository.deleteById(id);
    }

    public Pedido findByIdPedido(Integer id) {
        return pedidoRepository.findById(id).orElse(null);
    }
}