package com.example.demoSan.controllers;

import com.example.demoSan.dao.ProcedureDAO;
import com.example.demoSan.models.Procedure;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @GetMapping("/allprocedures")
    public String showProcedures(Model model){
        model.addAttribute("procedures", ProcedureDAO.allProcedures());
        return "manager/proceduresList";
    }

    @GetMapping("/allprocedures/{procedureId}")
    public String showProcedure(@PathVariable("procedureId") int procedureId,
                                Model model){
        model.addAttribute("procedure", ProcedureDAO.showProcedure(procedureId));
        return "manager/procedure";
    }

    @GetMapping("/allprocedures/{procedureId}/edit")
    public String editProcedure(@PathVariable("procedureId") int procedureId,
                                Model model){
        model.addAttribute("procedureId", procedureId);
        return "manager/editProcedure";
    }

    @PostMapping("/allprocedures/{procedureId}")
    public String updateProcedure(@ModelAttribute("name") String name,
                                  @ModelAttribute("price") int price,
                                  @ModelAttribute("start_at") String start_at,
                                  @ModelAttribute("end_at") String end_at,
                                  @PathVariable("procedureId") int procedureId,
                                  BindingResult bindingResult,
                                  Model model) throws ParseException {
        if(bindingResult.hasErrors()) return "manager/proceduresList";
        Procedure procedure = new Procedure(procedureId, name, price, start_at, end_at);
        System.out.println(procedure.getName()+ procedure.getPrice());
        ProcedureDAO.editProcedure(procedureId, procedure);
        return "redirect:/manager/allprocedures/{procedureId}";
    }
}
