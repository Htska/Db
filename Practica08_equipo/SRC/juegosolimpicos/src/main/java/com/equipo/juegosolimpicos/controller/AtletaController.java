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
    // Inyectamos el servicio de atleta
    @Autowired
    AtletaService atletaService;

    /**
     * Método para obtener los atletas, si se recibe un número de pasaporte se
     * obtiene dicho atleta, en otro caso se buscan todos los atletas. Este
     * método se utiliza para la interfaz de thymeleaf.
     * 
     * @param numeroPasaporte número de pasaporte del atleta que se desea obtener.
     *                        Opcional
     * @param model           El modelo que se utilizará para enviar los datos a la
     *                        vista
     * @return La vista de atletas el cual está definido como Atletas.html.
     */
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

    /**
     * Método para mostrar el formulario de añadir atleta
     * 
     * @param model El modelo que se utilizará para enviar los datos a la vista
     * @return La vista de atletasAdd el cual está definido como atletasAdd.html.
     */
    @GetMapping("/atletasAdd")
    public String showNewForm(Model model) {
        Atleta atleta = new Atleta();
        model.addAttribute("atleta", atleta);
        model.addAttribute("pageTitle", "Añadir Atleta");
        return "atletasAdd";
    }

    /**
     * Método para añadir un atleta
     * 
     * @param atleta             objeto de tipo Atleta el cual guardaremos en la
     *                           base de datos.
     * @param redirectAttributes Objeto que se utiliza para enviar mensajes a la
     *                           vista
     * @return Redirige a la lista de atletas
     */
    @PostMapping("/atletas/save")
    public String addDisciplina(@ModelAttribute Atleta atleta, RedirectAttributes redirectAttributes) {
        atletaService.saveAtleta(atleta);
        redirectAttributes.addFlashAttribute("message", "Atleta ha sido guardado con éxito");
        return "redirect:/atletas";
    }

    /**
     * Método para mostrar el formulario de editar atleta
     * 
     * @param numeroPasaporte número de pasaporte del atleta que se desea editar.
     *                        (Se tomará como parte de PathVariable)
     * @param model           El modelo que se utilizará para enviar los datos a la
     *                        vista
     * @return La vista de atletasEdit el cual está definido como atletasEdit.html.
     */
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

    /**
     * Método para actualizar un atleta
     * 
     * @param atleta             objeto de tipo Atleta el cual actualizaremos en la
     *                           base de datos.
     * @param redirectAttributes Objeto que se utiliza para enviar mensajes a la
     *                           vista
     * @return Redirige a la lista de atletas
     */
    @PostMapping("/atletas/guardar")
    public String updateDisciplina(@ModelAttribute Atleta atleta, RedirectAttributes redirectAttributes) {
        atletaService.updateAtleta(atleta);
        redirectAttributes.addFlashAttribute("message", "Atleta ha sido editado con éxito");
        return "redirect:/atletas";
    }

    /**
     * Método para eliminar un atleta
     * 
     * @param numeroPasaporte número de pasaporte del atleta que se desea eliminar.
     *                        (Se tomará como parte de PathVariable)
     * @return Redirige a la lista de atletas
     */
    @GetMapping("/atleta/eliminar/{numeroPasaporte}")
    public String deleteDisciplina(@PathVariable("numeroPasaporte") String numeroPasaporte) {
        atletaService.deleteAtletaByNumeroPasaporte(numeroPasaporte);
        return "redirect:/atletas";
    }
}
