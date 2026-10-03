package com.novosiga.novosiga.controller;

import com.novosiga.novosiga.entity.Aluno;
import com.novosiga.novosiga.entity.Pedido;
import com.novosiga.novosiga.entity.Produto;
import com.novosiga.novosiga.service.AlunoService;
import com.novosiga.novosiga.service.PedidoService;
import com.novosiga.novosiga.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Pedido pedido,
                         @RequestParam(value = "aluno", required = false) Aluno alunoParam) {
        if (pedido.getAluno() == null && alunoParam != null) {
            pedido.setAluno(alunoParam);
        }
        pedidoService.salvarPedido(pedido);
        return "redirect:/pedidos/listar";
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> salvarJson(@RequestBody Pedido pedido) {
        if (pedido.getAluno() != null && pedido.getAluno().getIdAluno() != null) {
            Aluno aluno = alunoService.findByIdAluno(pedido.getAluno().getIdAluno());
            pedido.setAluno(aluno);
        }
        Pedido salvo = pedidoService.salvarPedido(pedido);
        return ResponseEntity.ok(java.util.Map.of(
            "idPedido", salvo.getIdPedido(),
            "status", "sucesso",
            "mensagem", "Pedido salvo com sucesso!"
        ));
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        List<Pedido> pedidos = pedidoService.findAll();
        model.addAttribute("pedidos", pedidos);
        return "pedido/listarPedidos";
    }

    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("alunos", alunoService.findAll());
        model.addAttribute("produtos", produtoService.findAll());
        return "pedido/formularioPedido";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        pedidoService.deleteById(id);
        return "redirect:/pedidos/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        Pedido pedido = pedidoService.findByIdPedido(id);
        model.addAttribute("pedido", pedido);
        model.addAttribute("alunos", alunoService.findAll());
        model.addAttribute("produtos", produtoService.findAll());
        return "pedido/formularioPedido";
    }
}