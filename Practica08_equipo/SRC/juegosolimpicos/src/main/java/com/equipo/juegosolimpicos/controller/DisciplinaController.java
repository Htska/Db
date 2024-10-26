package com.equipo.juegosolimpicos.controller;

import com.equipo.juegosolimpicos.model.Disciplina;
import com.equipo.juegosolimpicos.service.DisciplinaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Controller sirve para regresar los htmls, si se usara RestController regresaría cadenas
@Controller
public class DisciplinaController {
    @Autowired
    DisciplinaService disciplinaService;

    @GetMapping("/disciplinas")
    public String listDisciplinas(@RequestParam(value = "nombreDisciplina", required = false) String nombreDisciplina,
            Model model) {
        List<Disciplina> listaDisciplinas;

        if (nombreDisciplina != null && !nombreDisciplina.isEmpty()) {
            Disciplina disciplina = disciplinaService.getDisciplinaByNombreDisciplina(nombreDisciplina);
            listaDisciplinas = (disciplina != null) ? List.of(disciplina) : List.of();
            model.addAttribute("isSearch", true);

        } else {
            listaDisciplinas = disciplinaService.getAllDisciplinas();
            model.addAttribute("isSearch", false);

        }

        model.addAttribute("listaDisciplinas", listaDisciplinas);
        return "disciplinas";
    }

    // Funciona pero no puedo cambiar la url a /Disciplina/añadir sin que de un
    // error
    @GetMapping("/disciplinasAdd")
    public String showNewForm(Model model) {
        model.addAttribute("disciplina", new Disciplina());
        model.addAttribute("pageTitle", "Añadir Disciplina");
        return "disciplinasAdd";
    }

    @PostMapping("/disciplinas/save")
    public String addDisciplina(@ModelAttribute Disciplina disciplina, RedirectAttributes redirectAttributes) {
        disciplinaService.saveDisciplina(disciplina);
        redirectAttributes.addFlashAttribute("message", "La disciplina ha sido guardada con éxito");
        return "redirect:/disciplinas";
    }

    @GetMapping("/disciplina/editar/{nombreDisciplina}")
    public String showEditForm(@PathVariable("nombreDisciplina") String nombreDisciplina, Model model) {
        try {
            Disciplina disciplina = disciplinaService.getDisciplinaByNombreDisciplina(nombreDisciplina);
            model.addAttribute("disciplina", disciplina);
            model.addAttribute("pageTitle", "Editar Disciplina");
            return "disciplinasEdit";
        } catch (Exception e) {
            return "redirect:/disciplinas";
        }
    }

    // La versión anterior tenía @PutMapping
    @PostMapping("/disciplinas/guardar")
    public String updateDisciplina(@ModelAttribute Disciplina disciplina, RedirectAttributes redirectAttributes) {
        disciplinaService.updateDisciplina(disciplina);
        redirectAttributes.addFlashAttribute("message", "La disciplina ha sido editada con éxito");
        return "redirect:/disciplinas";
    }

    /*
     * // Hay que hacer un htlml para mostrar una sola disciplina y adaptar el
     * método.
     * 
     * @GetMapping("/disciplina/{nombreDisciplina}")
     * public Disciplina getDisciplina(@PathVariable("nombreDisciplina") String
     * nombreDisciplina) {
     * return disciplinaService.getDisciplinaByNombreDisciplina(nombreDisciplina);
     * }
     */

    // La versión anterior tenía
    // @DeleteMapping("/disciplina/eliminar/{nombreDisciplina}")
    @GetMapping("/disciplina/eliminar/{nombreDisciplina}")
    public String deleteDisciplina(@PathVariable("nombreDisciplina") String nombreDisciplina) {
        disciplinaService.deleteDisciplinaByNombreDisciplina(nombreDisciplina);
        return "redirect:/disciplinas";
    }
}
