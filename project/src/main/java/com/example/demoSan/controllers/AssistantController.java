package com.example.demoSan.controllers;

import com.example.demoSan.dao.*;
import com.example.demoSan.models.PurchaseWorker;
import com.example.demoSan.models.Treatment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/assistant")
public class AssistantController {

    // список всех рабочих
    @GetMapping("/")
    public String list(Model model){
        model.addAttribute("workers", WorkerDAO.workersList());
        return "assistant/workerslist";
    }

    // страница аккаунта
@GetMapping("/{id}")
    public String showWorker(@PathVariable("id") int WorkerId,
                             Model model){
        model.addAttribute("worker", WorkerDAO.showWorker(WorkerId));
        model.addAttribute("myProcedures", ProcedureDAO.boughtProcedures(WorkerId));
        model.addAttribute("workerId", WorkerId);
        return "assistant/workershow";
    }

    // список всех процедур
    @GetMapping("/{id}/allprocedures")
    public String allProcedures(@PathVariable("id") int WorkerId,
                                Model model){
        model.addAttribute("worker", WorkerDAO.showWorker(WorkerId));
        model.addAttribute("myProcedures", ProcedureDAO.boughtProcedures(WorkerId));
        model.addAttribute("restProcedures", ProcedureDAO.restProcedures(WorkerId));
        model.addAttribute("workerId", WorkerId);
        return "assistant/procedurelist";
    }

    // поиск процедуры
    @PostMapping("/{id}/findprocedure")
    public String findProcedure(@RequestParam String name,
                                @PathVariable("id") int WorkerId,
                                Model model){
        if (ProcedureDAO.findProcedureByName(name) != null) {
            model.addAttribute("worker", WorkerDAO.showWorker(WorkerId));
            model.addAttribute("myProcedures", ProcedureDAO.boughtProcedure(WorkerId, ProcedureDAO.findProcedureByName(name).getId()));
            model.addAttribute("restProcedures", ProcedureDAO.restProcedure(WorkerId, ProcedureDAO.findProcedureByName(name).getId()));
            model.addAttribute("workerId", WorkerId);
            return "findprocedure";
        }
        return "redirect:/assistant/" + WorkerId + "/allprocedures";
    }

    // вывод определенной процедуры
    @GetMapping("/{id}/allprocedures/{ProcedureId}")
    public String showProcedure(@PathVariable("id") int WorkerId,
                                @PathVariable("ProcedureId") int ProcedureId,
                                Model model){
        model.addAttribute("worker", WorkerDAO.showWorker(WorkerId));
        model.addAttribute("procedure", ProcedureDAO.showProcedure(ProcedureId));
        model.addAttribute("clients", ClientDAO.getClientByProcedure(ProcedureId));
        model.addAttribute("numberOfClients", ClientDAO.getClientByProcedure(ProcedureId).size());
        model.addAttribute("workerId", WorkerId);
        return "assistant/showprocedure";
    }

    // подтверждение покупки процедуры
    @GetMapping("/{id}/allprocedures/add{ProcedureId}")
    public String addProcedure(Model model,
                               @PathVariable("id") int WorkerId,
                               @PathVariable("ProcedureId") int ProcedureId){
        // данные о сотруднике для отображения его имени
        model.addAttribute("worker", WorkerDAO.showWorker(WorkerId));
        // данные о процедуре для отображения названия
        model.addAttribute("procedure", ProcedureDAO.showProcedure(ProcedureId));
        return "assistant/addprocedure";
    }

    // ПОСТ-запрос для сохранения покупки
    // с помощью ModelAttribute получаем данные для сохранения покупки в БД
    @PostMapping("/{id}/allprocedures")
    public String savePurchase(@ModelAttribute("procedureId") int ProcedureID,
                               @ModelAttribute("workerId") int WorkerID,
                               @PathVariable("id") int id,
                               BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "procedurelist";
        // создаём объект для сохранения его в БД
        PurchaseWorker p = new PurchaseWorker(WorkerID, ProcedureID);
        TreatmentPurchaseDAO.addPurchaseW(p);
        // перенаправление на чек. (отправит на чек последней покупки)
        return "redirect:/assistant/"+ id +"/check" + TreatmentPurchaseDAO.showLastPurchase().getPurchaseID();
    }

    // отображение чека с покупки
    @GetMapping("/{id}/check{checkN}")
    public String showCheck(@PathVariable("checkN") int PurchaseId,
                            Model model){
        // создаём объект Покупки, чтобы извлечь ID процедуры и клиента
        PurchaseWorker purchase = TreatmentPurchaseDAO.showPurchaseW(PurchaseId);
        model.addAttribute("worker", WorkerDAO.showWorker(purchase.getWorkerID()));
        model.addAttribute("procedure", ProcedureDAO.showProcedure(purchase.getProcedureID()));
        model.addAttribute("check", purchase);
        return "assistant/purchasecheck";
    }

    // список всех пациентов
    @GetMapping("/{id}/allclients")
    public String allclients(@PathVariable("id") int WorkerId,
                             Model model){
        model.addAttribute("worker", WorkerDAO.showWorker(WorkerId));
        model.addAttribute("allClients", ClientDAO.clientList());
        model.addAttribute("workerId", WorkerId);
        return "assistant/clientlist";
    }

    // поиск пациента
    @PostMapping("/{id}/findclient")
    public String findClient(@RequestParam String name,
                             @PathVariable("id") int WorkerId,
                             Model model){
        if (ClientDAO.findClientByName(name) != null) {
            model.addAttribute("worker", WorkerDAO.showWorker(WorkerId));
            model.addAttribute("client", ClientDAO.showClient(ClientDAO.findClientByName(name).getId()));
            model.addAttribute("workerId", WorkerId);
            return "assistant/findclient";
        }
        return "redirect:/assistant/" + WorkerId + "/allclients";
    }

    // вывод определенного пациента
    @GetMapping("/{id}/allclients/{ClientId}")
    public String showClient(@PathVariable("id") int WorkerId,
                             @PathVariable("ClientId") int ClientId,
                             Model model){
        model.addAttribute("worker", WorkerDAO.showWorker(WorkerId));
        model.addAttribute("client", ClientDAO.showClient(ClientId));
        model.addAttribute("reservation", ClientDAO.showReservation(ClientId));
        model.addAttribute("myProcedures", ProcedureDAO.myProcedures(ClientId));
        model.addAttribute("boughtProcedures", ProcedureDAO.clientBoughtProcedures(ClientId));
        model.addAttribute("workerId", WorkerId);
        return "assistant/showclient";
    }

    // отметить процедуру как посещенную и добавить в таблицу treatment
    @PostMapping("/{id}/allclients/{ClientId}")
    public String setProcedureAsVisited(@PathVariable("id") int WorkerId,
                               @ModelAttribute("procedureId") int procedureID,
                               @ModelAttribute("clientId") int clientID,
                               BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "clientlist";
        ProcedureDAO.setProcedureAsVisited(procedureID,clientID);
        Treatment t = new Treatment(clientID, procedureID);
        TreatmentPurchaseDAO.addTreatment(t);
        return "redirect:/assistant/"+ WorkerId +"/allclients";
    }

    // расписание процедур
    @GetMapping("/schedule")
    public String proceduresSchedule(Model model){
        model.addAttribute("Monday", ProcedureDAO.procedureSchedule(1));
        model.addAttribute("Tuesday", ProcedureDAO.procedureSchedule(2));
        model.addAttribute("Wednesday", ProcedureDAO.procedureSchedule(3));
        model.addAttribute("Thursday", ProcedureDAO.procedureSchedule(4));
        model.addAttribute("Friday", ProcedureDAO.procedureSchedule(5));
        model.addAttribute("Saturday", ProcedureDAO.procedureSchedule(6));
        model.addAttribute("Sunday", ProcedureDAO.procedureSchedule(7));

        return "assistant/schedule";
    }

}
