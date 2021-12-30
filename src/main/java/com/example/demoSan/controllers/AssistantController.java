package com.example.demoSan.controllers;

import com.example.demoSan.dao.*;
import com.example.demoSan.models.PurchaseWorker;
import com.example.demoSan.models.Treatment;
import com.example.demoSan.security.IUserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/assistant")
public class AssistantController {

    @Autowired
    private IUserId userId;

    // страница аккаунта
    @GetMapping("")
    public String showWorker(Model model){
        model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
        model.addAttribute("myProcedures", ProcedureDAO.boughtProcedures(userId.get()));
        model.addAttribute("workerId", userId.get());
        return "assistant/workershow";
    }

    // список всех процедур
    @GetMapping("/allprocedures")
    public String allProcedures(Model model){
        model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
        model.addAttribute("myProcedures", ProcedureDAO.boughtProcedures(userId.get()));
        model.addAttribute("restProcedures", ProcedureDAO.restProceduresW(userId.get()));
        model.addAttribute("workerId", userId.get());
        return "assistant/procedurelist";
    }

    // поиск процедуры
    @PostMapping("/findprocedure")
    public String findProcedure(@RequestParam String name,
                                Model model){
        if (ProcedureDAO.findProcedureByName(name) != null) {
            model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
            model.addAttribute("myProcedures", ProcedureDAO.boughtProcedure(userId.get(), ProcedureDAO.findProcedureByName(name).getId()));
            model.addAttribute("restProcedures", ProcedureDAO.restProcedure(userId.get(), ProcedureDAO.findProcedureByName(name).getId()));
            model.addAttribute("workerId", userId.get());
            return "assistant/findprocedure";
        }
        return "redirect:/assistant/allprocedures";
    }

    // вывод определенной процедуры
    @GetMapping("/allprocedures/{ProcedureId}")
    public String showProcedure(@PathVariable("ProcedureId") int ProcedureId,
                                Model model){
        model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
        model.addAttribute("procedure", ProcedureDAO.showProcedure(ProcedureId));
        model.addAttribute("clients", ClientDAO.getClientByProcedure(ProcedureId));
        model.addAttribute("numberOfClients", ClientDAO.getClientByProcedure(ProcedureId).size());

        model.addAttribute("workerId", userId.get());
        return "assistant/showprocedure";
    }

    // подтверждение покупки процедуры
    @GetMapping("/allprocedures/add{ProcedureId}")
    public String addProcedure(Model model,
                               @PathVariable("ProcedureId") int ProcedureId){
        // данные о сотруднике для отображения его имени
        model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
        // данные о процедуре для отображения названия
        model.addAttribute("procedure", ProcedureDAO.showProcedure(ProcedureId));
        return "assistant/addprocedure";
    }

    // ПОСТ-запрос для сохранения покупки
    // с помощью ModelAttribute получаем данные для сохранения покупки в БД
    @PostMapping("/allprocedures")
    public String savePurchase(@ModelAttribute("procedureId") int ProcedureID,
                               @ModelAttribute("workerId") int WorkerID,
                               BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "procedurelist";
        // создаём объект для сохранения его в БД
        PurchaseWorker p = new PurchaseWorker(WorkerID, ProcedureID);
        TreatmentPurchaseDAO.addPurchaseW(p);
        // перенаправление на чек. (отправит на чек последней покупки)
        return "redirect:/assistant/check" + TreatmentPurchaseDAO.showLastPurchaseW().getPurchaseID();
    }

    // отображение чека с покупки
    @GetMapping("/check{checkN}")
    public String showCheck(@PathVariable("checkN") int PurchaseId,
                            Model model){
        // создаём объект Покупки, чтобы извлечь ID процедуры и клиента
        PurchaseWorker purchaseWorker = TreatmentPurchaseDAO.showPurchaseW(PurchaseId);
        model.addAttribute("worker", WorkerDAO.showWorker(purchaseWorker.getWorkerID()));
        model.addAttribute("procedure", ProcedureDAO.showProcedure(purchaseWorker.getProcedureID()));
        model.addAttribute("check", purchaseWorker);
        return "assistant/purchasecheck";
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

    // поиск пациента
    @PostMapping("/findclient")
    public String findClient(@RequestParam String name,
                             Model model){
        if (ClientDAO.findClientByName(name) != null) {
            model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
            model.addAttribute("client", ClientDAO.showClient(ClientDAO.findClientByName(name).getId()));
            model.addAttribute("workerId", userId.get());
            return "assistant/findclient";
        }
        return "redirect:/assistant/allclients";
    }

    // вывод определенного пациента
    @GetMapping("/allclients/{ClientId}")
    public String showClient(@PathVariable("ClientId") int ClientId,
                             Model model){
        model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
        model.addAttribute("client", ClientDAO.showClient(ClientId));
        model.addAttribute("reservation", ClientDAO.showReservation(ClientId));
        model.addAttribute("myProcedures", TreatmentPurchaseDAO.showLastTreatments(ClientId));
//        model.addAttribute("myProcedures", ProcedureDAO.myProcedures(ClientId));
        model.addAttribute("boughtProcedures", ProcedureDAO.clientBoughtProcedures(ClientId));
        model.addAttribute("workerId", userId.get());
        return "assistant/showclient";
    }

    // отметить процедуру как посещенную и добавить в таблицу treatment
    @PostMapping("/allclients/{ClientId}")
    public String setProcedureAsVisited(@ModelAttribute("procedureId") int procedureID,
                               @ModelAttribute("clientId") int clientID,
                               BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "clientlist";
        ProcedureDAO.setProcedureAsVisited(procedureID,clientID);
        Treatment t = new Treatment(clientID, procedureID);
        TreatmentPurchaseDAO.addTreatment(t);
            return "redirect:/assistant/allclients";
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
