package com.example.demoSan.controllers;

import com.example.demoSan.dao.*;
import com.example.demoSan.models.Worker;
import com.example.demoSan.security.IUserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/director")
public class DirectorController {

    @Autowired
    private IUserId userId;

    // страница аккаунта
    @GetMapping("")
    public String homePage(Model model){
        model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
        model.addAttribute("myProcedures", ProcedureDAO.boughtProcedures(userId.get()));
        model.addAttribute("workerId", userId.get());
        return "director/directorHome";
    }

    // список всех рабочих
    @GetMapping("/allemployees")
    public String allEmployees(Model model){
        model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
        model.addAttribute("allEmployees", WorkerDAO.workersList());
        model.addAttribute("workerId", userId.get());
        return "director/employees";
    }

    // поиск рабочего
    @PostMapping("/findemployee")
    public String findEmployee(@RequestParam String name,
                               Model model){
        if (WorkerDAO.findWorkerByName(name) != null) {
            model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
            model.addAttribute("employee", WorkerDAO.showWorker(WorkerDAO.findWorkerByName(name).getWorkerId()));
            model.addAttribute("workerId", userId.get());
            return "director/findEmployee";
        }
        return "redirect:/director/allemployees";
    }

    // форма для добавления нового рабочего
    @GetMapping("/addemployee")
    public String addEmployee(Model model){
        model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
        model.addAttribute("workers", new Worker());
        return "director/addEmployee";
    }

    // добавление нового рабочего
    @PostMapping("/addemployee")
    public String createEmployee(@ModelAttribute("workers") @Valid Worker worker,
                                 BindingResult bindingResult,
                                 Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
            return "director/addEmployee";
        }
        WorkerDAO.save(worker);
        return "redirect:/director/allemployees";
    }

    // форма для изменения данных рабочего
    @GetMapping("/editemployee/{employeeId}")
    public String editEmployee(@PathVariable("employeeId") int employeeId,
                              Model model){
        model.addAttribute("workerId", userId.get());
        model.addAttribute("employee", WorkerDAO.showWorker(employeeId));
        model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
        return "director/editEmployee";
    }

    // изменения рабочего
    @PostMapping("/editemployee/{employeeId}")
    public String updateEmployee(@ModelAttribute("worker") Worker worker,
                                 @PathVariable("employeeId") int employeeId,
                                 Model model,
                                 BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "/director/directorHome";
        model.addAttribute("workerId", userId.get());
        WorkerDAO.update(employeeId, worker);
        return "redirect:/director/allemployees";
    }

    // удаление рабочего
    @PostMapping("/deleteEmployee/{employeeId}")
    public String deleteEmployee(@PathVariable("employeeId") int employeeId){
        WorkerDAO.delete(employeeId);
        return "redirect:/director/allemployees";
    }
}
