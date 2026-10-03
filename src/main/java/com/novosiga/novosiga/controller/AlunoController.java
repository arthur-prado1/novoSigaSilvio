package com.novosiga.novosiga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.novosiga.novosiga.entity.Aluno;
import com.novosiga.novosiga.entity.Curso;
import com.novosiga.novosiga.service.AlunoService;
import com.novosiga.novosiga.service.CursoService;

@Controller
@RequestMapping("/alunos")
public class AlunoController {
    
    // Injeção de dependências da service de alunos e cursos
    @Autowired
    private AlunoService alunoService;

    @Autowired
    private CursoService cursoService;

    // Método para salvar um aluno
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Aluno aluno,
                         @RequestParam("foto") MultipartFile foto,
                         @RequestParam(value = "curso", required = false) Curso cursoParam) {
        try {
            if (!foto.isEmpty()) {
                aluno.setFotoAluno(foto.getBytes());
                aluno.setTipoFoto(foto.getContentType());
            } else if (aluno.getIdAluno() != null) {
                Aluno alunoExistente = alunoService.findByIdAluno(aluno.getIdAluno());
                if (alunoExistente != null) {
                    aluno.setFotoAluno(alunoExistente.getFotoAluno());
                    aluno.setTipoFoto(alunoExistente.getTipoFoto());
                }
            }

            if (aluno.getCurso() == null && cursoParam != null) {
                aluno.setCurso(cursoParam);
            }

            alunoService.save(aluno);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/alunos/listar";
    }

    // Método para listar todos os alunos
    @GetMapping("/listar")
    public String listar(Model model) {
        List<Aluno> alunos = alunoService.findAll();
        model.addAttribute("alunos", alunos);
        return "aluno/listarAlunos";
    }

    // Método para abrir o formulário para cadastro de aluno
    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("aluno", new Aluno());
        List<Curso> cursos = cursoService.findAll();
        model.addAttribute("cursos", cursos);
        return "aluno/formularioAluno";
    }

    // Método para excluir um aluno pelo ID
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        alunoService.deleteById(id);
        return "redirect:/alunos/listar";
    }

    // Método para abrir o formulário de edição de aluno
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        Aluno aluno = alunoService.findByIdAluno(id);
        model.addAttribute("aluno", aluno);
        List<Curso> cursos = cursoService.findAll();
        model.addAttribute("cursos", cursos);
        return "aluno/formularioAluno";
    }

    @GetMapping("/foto/{id}")
    public ResponseEntity<byte[]> foto(@PathVariable Integer id) {
        Aluno aluno = alunoService.findByIdAluno(id);
        if (aluno == null || aluno.getFotoAluno() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(org.springframework.http.MediaType.parseMediaType(aluno.getTipoFoto()))
                .body(aluno.getFotoAluno());
    }
}