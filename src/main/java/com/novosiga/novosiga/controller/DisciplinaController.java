package com.novosiga.novosiga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.novosiga.novosiga.entity.Curso;
import com.novosiga.novosiga.entity.Disciplina;
import com.novosiga.novosiga.entity.Professor;
import com.novosiga.novosiga.service.CursoService;
import com.novosiga.novosiga.service.DisciplinaService;
import com.novosiga.novosiga.service.ProfessorService;

@Controller
@RequestMapping("/disciplinas")
public class DisciplinaController {
    
    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private CursoService cursoService;

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Disciplina disciplina,
                         @RequestParam(value = "professor", required = false) Professor professorParam,
                         @RequestParam(value = "curso", required = false) Curso cursoParam) {
        if (disciplina.getProfessor() == null && professorParam != null) {
            disciplina.setProfessor(professorParam);
        }
        if (disciplina.getCurso() == null && cursoParam != null) {
            disciplina.setCurso(cursoParam);
        }
        disciplinaService.save(disciplina);
        return "redirect:/disciplinas/listar";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        List<Disciplina> disciplinas = disciplinaService.findAll();
        model.addAttribute("disciplinas", disciplinas);
        return "disciplina/listarDisciplinas";
    }

    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("disciplina", new Disciplina());
        List<Professor> professores = professorService.findAll();
        model.addAttribute("professores", professores);
        List<Curso> cursos = cursoService.findAll();
        model.addAttribute("cursos", cursos);
        return "disciplina/formularioDisciplina";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        disciplinaService.deleteById(id);
        return "redirect:/disciplinas/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        Disciplina disciplina = disciplinaService.findByIdDisciplina(id);
        model.addAttribute("disciplina", disciplina);
        List<Professor> professores = professorService.findAll();
        model.addAttribute("professores", professores);
        List<Curso> cursos = cursoService.findAll();
        model.addAttribute("cursos", cursos);
        return "disciplina/formularioDisciplina";
    }
}
