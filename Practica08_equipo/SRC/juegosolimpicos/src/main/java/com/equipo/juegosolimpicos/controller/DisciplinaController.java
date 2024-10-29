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
    // Inyectamos el servicio de disciplina
    @Autowired
    DisciplinaService disciplinaService;

    /**
     * Método para obtener las disciplinas, si se recibe un nombre de disciplina se
     * obtiene dicha disciplina, en otro caso se buscan todas las disciplinas. Este
     * método se utiliza para la interfaz de thymeleaf.
     * 
     * @param nombreDisciplina nombre de la disciplina que se desea obtener.
     *                         Opcional
     * @param model            El modelo que se utilizará para enviar los datos a la
     *                         vista
     * @return La vista de disciplinas el cual está definido como disciplinas.html.
     */
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

    /**
     * Método para mostrar el formulario de añadir disciplina
     * 
     * @param model El modelo que se utilizará para enviar los datos a la vista
     * @return La vista de disciplinasAdd el cual está definido como
     *         disciplinasAdd.html.
     */
    @GetMapping("/disciplinasAdd")
    public String showNewForm(Model model) {
        model.addAttribute("disciplina", new Disciplina());
        model.addAttribute("pageTitle", "Añadir Disciplina");
        return "disciplinasAdd";
    }

    /**
     * Método para añadir una disciplina
     * 
     * @param disciplina         La disciplina que se desea añadir, debe de cumplir
     *                           con el modelo especificado.
     * @param redirectAttributes Atributos que se enviarán a la vista.
     * @return Vista de disciplinas.
     */
    @PostMapping("/disciplinas/save")
    public String addDisciplina(@ModelAttribute Disciplina disciplina, RedirectAttributes redirectAttributes) {
        disciplinaService.saveDisciplina(disciplina);
        redirectAttributes.addFlashAttribute("message", "La disciplina ha sido guardada con éxito");
        return "redirect:/disciplinas";
    }

    /**
     * Método para mostrar el formulario de editar disciplina
     * 
     * @param nombreDisciplina El nombre de la disciplina que se desea editar. (Se
     *                         tomará como parte de PathVariable)
     * @param model            El modelo que se utilizará para enviar los datos a la
     *                         vista
     * @return La vista de disciplinasEdit el cual está definido como
     *         disciplinasEdit.html.
     */
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

    /**
     * Método para editar una disciplina
     * 
     * @param disciplina         La disciplina que se desea editar, debe de cumplir
     *                           con el modelo especificado.
     * @param redirectAttributes Atributos que se enviarán a la vista.
     * @return Vista de disciplinas.
     */
    @PostMapping("/disciplinas/guardar")
    public String updateDisciplina(@ModelAttribute Disciplina disciplina, RedirectAttributes redirectAttributes) {
        disciplinaService.updateDisciplina(disciplina);
        redirectAttributes.addFlashAttribute("message", "La disciplina ha sido editada con éxito");
        return "redirect:/disciplinas";
    }

    /**
     * Método para eliminar una disciplina
     * 
     * @param nombreDisciplina El nombre de la disciplina que se desea eliminar.(Se
     *                         tomará como parte de PathVariable)
     * @return Vista de disciplinas.
     */
    @GetMapping("/disciplina/eliminar/{nombreDisciplina}")
    public String deleteDisciplina(@PathVariable("nombreDisciplina") String nombreDisciplina) {
        disciplinaService.deleteDisciplinaByNombreDisciplina(nombreDisciplina);
        return "redirect:/disciplinas";
    }
}
