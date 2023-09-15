package br.edu.ifto.aula24082023.controller;
import br.edu.ifto.aula24082023.model.entity.Medico;
import br.edu.ifto.aula24082023.model.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Controller
@RequestMapping("medico")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;

    @GetMapping("/form")
    public String form(Medico medico){
        return "/medicos/form";
    }

    @ResponseBody
    @RequestMapping("list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("medico", repository.medicos());
        return new ModelAndView("/medicos/lista", model);
    }

    @PostMapping("/save")
    public ModelAndView save(Medico medico){
         repository.save(medico);
        return new ModelAndView("redirect:/medico/list");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        repository.remove(id);
        return new ModelAndView("redirect:/medico/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("medico", repository.medico(id));
        return new ModelAndView("/medicos/form");
    }

    @PostMapping("/update")
    public ModelAndView update(Medico medico) {
        repository.update(medico);
        return new ModelAndView("redirect:/medico/list");
    }
/*
    @GetMapping("/medico/{id}")
    public ModelAndView medico(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("medico", repository.medico(id));
        return new ModelAndView("/medicos/consultas");
    }
*/
    @GetMapping("/consultas/{id}")
    public ModelAndView medico(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("medico", repository.medico(id));
        return new ModelAndView("/medicos/consultas");
    }
}
