package com.novosiga.novosiga.service;

import com.novosiga.novosiga.entity.Produto;
import com.novosiga.novosiga.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public void deleteById(Integer id) {
        produtoRepository.deleteById(id);
    }

    public Produto findByIdProduto(Integer id) {
        return produtoRepository.findById(id).orElse(null);
    }
}
