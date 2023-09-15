package br.edu.ifto.aula24082023.controller;

import br.edu.ifto.aula24082023.model.entity.Consulta;
import br.edu.ifto.aula24082023.model.repository.ConsultaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Controller
@RequestMapping("consulta")
public class ConsultaController {
    @Autowired
    private ConsultaRepository repository;

    @GetMapping("/form")
    public String form(Consulta consulta) {
        return "/consultas/form";
    }

    @ResponseBody
    @RequestMapping("list")
    public ModelAndView listar(ModelMap model) {

        model.addAttribute("consulta", repository.consultas());
        model.addAttribute("valorTotal", repository.valorTotal());
        return new ModelAndView("consultas/lista", model);
    }

    @PostMapping("/save")
    public ModelAndView save(Consulta consulta) {
        repository.save(consulta);
        return new ModelAndView("redirect:/consulta/list");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        repository.remove(id);
        return new ModelAndView("redirect:/consulta/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("consulta", repository.consulta(id));
        return new ModelAndView("/consultas/form");
    }

    @PostMapping("/update")
    public ModelAndView update(Consulta consulta) {
        repository.update(consulta);
        return new ModelAndView("redirect:/consulta/list");
    }

}
