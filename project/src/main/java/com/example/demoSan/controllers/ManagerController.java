package com.example.demoSan.controllers;

import com.example.demoSan.dao.*;
import com.example.demoSan.models.Procedure;
import com.example.demoSan.security.IUserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private IUserId userId;

    @GetMapping("")
    public String showWorker(Model model){
        model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
        model.addAttribute("myProcedures", ProcedureDAO.boughtProcedures(userId.get()));
        model.addAttribute("workerId", userId.get());
        return "manager/main";
    }

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

    // список всех пациентов
    @GetMapping("/allclients")
    public String allclients(Model model){
        model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
        model.addAttribute("allClients", ClientDAO.clientList());
        model.addAttribute("workerId", userId.get());
        model.addAttribute("valueOfProc", ClientDAO.getClientWithMaxTreatment());
        return "assistant/clientlist";
    }

    @GetMapping("/allprocedures/{procedureId}/edit")
    public String editProcedure(@PathVariable("procedureId") int procedureId,
                                Model model){

        model.addAttribute("procedure", ProcedureDAO.showProcedure(procedureId));
        model.addAttribute("procedureId", procedureId);
        return "manager/editProcedure";
    }

    @PostMapping("/allprocedures/{procedureId}")
    public String updateProcedure(@PathVariable("procedureId") int procedureId,
                                  @ModelAttribute("procedure") /*@Valid*/ final Procedure procedure,
                                  final BindingResult bindingResult) {

        if(bindingResult.hasErrors())
            return "manager/editProcedure";

        ProcedureDAO.editProcedure(procedureId, procedure);
        return "redirect:/manager/allprocedures/{procedureId}";
    }

    @GetMapping("/allclients/max")
    public String maxProcedure(Model model){
        model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
        model.addAttribute("client", ClientDAO.getClientWithMaxTreatment());
        return "assistant/max";
    }

    @GetMapping("/allclients/min")
    public String minProcedure(Model model){
        model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
        model.addAttribute("client", ClientDAO.getClientWithMinTreatment());
        return "assistant/min";
    }
}