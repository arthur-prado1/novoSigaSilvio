package com.novosiga.novosiga.controller;

import com.novosiga.novosiga.entity.Produto;
import com.novosiga.novosiga.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Produto produto,
                         @RequestParam("foto") MultipartFile foto) {
        try {
            if (!foto.isEmpty()) {
                produto.setImagem(foto.getBytes());
                produto.setTipoImagem(foto.getContentType());
            } else if (produto.getIdProduto() != null) {
                Produto produtoExistente = produtoService.findByIdProduto(produto.getIdProduto());
                if (produtoExistente != null) {
                    produto.setImagem(produtoExistente.getImagem());
                    produto.setTipoImagem(produtoExistente.getTipoImagem());
                }
            }
            produtoService.save(produto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/produtos/listar";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        List<Produto> produtos = produtoService.findAll();
        model.addAttribute("produtos", produtos);
        return "produto/listarProdutos";
    }

    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("produto", new Produto());
        return "produto/formularioProduto";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        produtoService.deleteById(id);
        return "redirect:/produtos/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        Produto produto = produtoService.findByIdProduto(id);
        model.addAttribute("produto", produto);
        return "produto/formularioProduto";
    }

    @GetMapping("/imagem/{id}")
    public ResponseEntity<byte[]> imagem(@PathVariable Integer id) {
        Produto produto = produtoService.findByIdProduto(id);
        if (produto == null || produto.getImagem() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(org.springframework.http.MediaType.parseMediaType(produto.getTipoImagem()))
                .body(produto.getImagem());
    }
}