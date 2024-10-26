package com.equipo.juegosolimpicos.controller;

import com.equipo.juegosolimpicos.model.Atleta;
import com.equipo.juegosolimpicos.service.AtletaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Controller sirve para regresar los htmls, si se usara RestController regresaría cadenas
@Controller
public class AtletaController {
    @Autowired
    AtletaService atletaService;

    @GetMapping("/atletas")
    public String listAtletas(@RequestParam(value = "numeroPasaporte", required = false) String numeroPasaporte,
            Model model) {
        List<Atleta> listaAtletas;

        if (numeroPasaporte != null && !numeroPasaporte.isEmpty()) {
            Atleta atleta = atletaService.getAtletaByNumeroPasaporte(numeroPasaporte);
            listaAtletas = (atleta != null) ? List.of(atleta) : List.of(); // Show single result or empty list
            model.addAttribute("isSearch", true);

        } else {
            listaAtletas = atletaService.getAllAtletas(); // Show all athletes if no search term
            model.addAttribute("isSearch", false);

        }

        model.addAttribute("listaAtletas", listaAtletas);
        return "Atletas"; // Thymeleaf view name
    }

    @GetMapping("/atletasAdd")
    public String showNewForm(Model model) {
        Atleta atleta = new Atleta();
        model.addAttribute("atleta", atleta);
        model.addAttribute("pageTitle", "Añadir Atleta");
        return "atletasAdd";
    }

    @PostMapping("/atletas/save")
    public String addDisciplina(@ModelAttribute Atleta atleta, RedirectAttributes redirectAttributes) {
        atletaService.saveAtleta(atleta);
        redirectAttributes.addFlashAttribute("message", "Atleta ha sido guardado con éxito");
        return "redirect:/atletas";
    }

    @GetMapping("/atletas/editar/{numeroPasaporte}")
    public String showEditForm(@PathVariable("numeroPasaporte") String numeroPasaporte, Model model) {
        try {
            Atleta atleta = atletaService.getAtletaByNumeroPasaporte(numeroPasaporte);
            model.addAttribute("atleta", atleta);
            model.addAttribute("pageTitle", "Editar Atleta");
            return "atletasEdit";
        } catch (Exception e) {
            return "redirect:/atletas";
        }
    }

    // La versión anterior tenía @PutMapping
    @PostMapping("/atletas/guardar")
    public String updateDisciplina(@ModelAttribute Atleta atleta, RedirectAttributes redirectAttributes) {
        atletaService.updateAtleta(atleta);
        redirectAttributes.addFlashAttribute("message", "Atleta ha sido editado con éxito");
        return "redirect:/atletas";
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
    @GetMapping("/atleta/eliminar/{numeroPasaporte}")
    public String deleteDisciplina(@PathVariable("numeroPasaporte") String numeroPasaporte) {
        atletaService.deleteAtletaByNumeroPasaporte(numeroPasaporte);
        return "redirect:/atletas";
    }
}
