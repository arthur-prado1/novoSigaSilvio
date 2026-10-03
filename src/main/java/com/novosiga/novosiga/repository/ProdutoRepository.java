package com.novosiga.novosiga.repository;

import com.novosiga.novosiga.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
