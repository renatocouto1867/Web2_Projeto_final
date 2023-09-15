package br.edu.ifto.aula24082023.controller;

import br.edu.ifto.aula24082023.model.entity.Paciente;
import br.edu.ifto.aula24082023.model.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Transactional
@Controller
@RequestMapping("paciente")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;

    @GetMapping("/form")
    public String form(Paciente paciente) {
        return "/pacientes/form";
    }

    @ResponseBody
    @RequestMapping("list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("paciente", repository.pacientes());
        return new ModelAndView("/pacientes/lista", model);
    }

    @PostMapping("/save")
    public ModelAndView save(Paciente paciente) {
        repository.save(paciente);
        return new ModelAndView("redirect:/paciente/list");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        repository.remove(id);
        return new ModelAndView("redirect:/paciente/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("paciente", repository.paciente(id));
        return new ModelAndView("/pacientes/form");
    }

    @PostMapping("/update")
    public ModelAndView update(Paciente paciente) {
        repository.update(paciente);
        return new ModelAndView("redirect:/paciente/list");
    }

    @GetMapping("/consulta/{id}")
    public ModelAndView paciente(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("paciente", repository.paciente(id));
        return new ModelAndView("/pacientes/consultas");
    }
}
