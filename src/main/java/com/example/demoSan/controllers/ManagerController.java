package com.example.demoSan.controllers;

import com.example.demoSan.dao.*;
import com.example.demoSan.models.Client;
import com.example.demoSan.models.Procedure;
import com.example.demoSan.models.PurchaseWorker;
import com.example.demoSan.security.IUserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    // поиск процедуры
    @PostMapping("/findprocedure")
    public String findProcedure(@RequestParam String name,
                                Model model){
        if (ProcedureDAO.findProcedureByName(name) != null) {
            model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
            model.addAttribute("myProcedures", ProcedureDAO.boughtProcedure(userId.get(), ProcedureDAO.findProcedureByName(name).getId()));
            model.addAttribute("restProcedures", ProcedureDAO.restProcedure(userId.get(), ProcedureDAO.findProcedureByName(name).getId()));
            model.addAttribute("workerId", userId.get());
            return "manager/findprocedure";
        }
        return "redirect:/manager/allprocedures";
    }

    // подтверждение покупки процедуры
    @GetMapping("/allprocedures/add{ProcedureId}")
    public String addProcedure(Model model,
                               @PathVariable("ProcedureId") int ProcedureId){
        // данные о сотруднике для отображения его имени
        model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
        // данные о процедуре для отображения названия
        model.addAttribute("procedure", ProcedureDAO.showProcedure(ProcedureId));
        return "manager/addprocedure";
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
        return "redirect:/manager/check" + TreatmentPurchaseDAO.showLastPurchaseW().getPurchaseID();
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
        return "manager/purchasecheck";
    }

    @PostMapping("/findclient")
    public String findClient(@RequestParam String name,
                             Model model){
        if (ClientDAO.findClientByName(name) != null) {
            model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
            model.addAttribute("client", ClientDAO.showClient(ClientDAO.findClientByName(name).getId()));
            model.addAttribute("workerId", userId.get());
            return "manager/findclient";
        }
        return "redirect:/manager/allclients";
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
        return "manager/showclient";
    }

    // список всех процедур
    @GetMapping("/allprocedures")
    public String allProcedures(Model model){
        model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
        model.addAttribute("myProcedures", ProcedureDAO.boughtProcedures(userId.get()));
        model.addAttribute("restProcedures", ProcedureDAO.restProcedures(userId.get()));
        model.addAttribute("workerId", userId.get());
        return "manager/procedurelist";
    }

    // вывод определенной процедуры
    @GetMapping("/allprocedures/{ProcedureId}")
    public String showProcedure(@PathVariable("ProcedureId") int ProcedureId,
                                Model model){
        model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
        model.addAttribute("procedure", ProcedureDAO.showProcedure(ProcedureId));
        model.addAttribute("clients", ClientDAO.getClientByProcedure(ProcedureId));
        model.addAttribute("numberOfClients", ClientDAO.getClientByProcedure(ProcedureId).size());
        model.addAttribute("procedureId", ProcedureId);
        model.addAttribute("workerId", userId.get());
        return "manager/showprocedure";
    }

    // список всех пациентов
    @GetMapping("/allclients")
    public String allclients(Model model){
        model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
        model.addAttribute("allClients", ClientDAO.clientList());
        model.addAttribute("workerId", userId.get());
        model.addAttribute("valueOfProc", ClientDAO.getClientWithMaxTreatment());
        return "manager/clientlist";
    }

    @GetMapping("/allprocedures/{procedureId}/edit")
    public String editProcedure(@PathVariable("procedureId") int procedureId,
                                Model model){
        model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
        model.addAttribute("procedure", ProcedureDAO.showProcedure(procedureId));
        model.addAttribute("procedureId", procedureId);
        return "manager/editProcedure";
    }

    @PostMapping("/allprocedures/{procedureId}")
    public String updateProcedure(@PathVariable("procedureId") int procedureId,
                                  Model model,
                                  @ModelAttribute("procedure") @Valid final Procedure procedure,
                                  final BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
            return "manager/editProcedure";}
        model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
        ProcedureDAO.editProcedure(procedureId, procedure);
        return "redirect:/manager/allprocedures/{procedureId}";
    }

    @GetMapping("/allclients/max")
    public String maxProcedure(Model model){
        model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
        model.addAttribute("client", ClientDAO.getClientWithMaxTreatment());
        return "manager/max";
    }

    @GetMapping("/allclients/min")
    public String minProcedure(Model model){
        model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
        model.addAttribute("client", ClientDAO.getClientWithMinTreatment());
        return "manager/min";
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
        return "manager/schedule";
    }

    // форма для изменения данных пациента
    @GetMapping("/editclient-{clientId}")
    public String editEmployee(@PathVariable("clientId") int clientId,
                               Model model){
        model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
        model.addAttribute("client", ClientDAO.showClient(clientId));
        return "/manager/editClient";
    }

    // изменения пациента
    @PostMapping("/editclient-{clientId}")
    public String updateClient(@ModelAttribute("client") @Valid Client client,
                               BindingResult bindingResult,
                               @PathVariable("clientId") int clientId,
                               Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
            return "/manager/editClient";}
        model.addAttribute("worker", WorkerDAO.showWorker(userId.get()));
        ClientDAO.update(clientId, client);
        return "redirect:/manager/allclients";
    }

    // удаление пациента
    @PostMapping("/deleteClient-{clientId}")
    public String deleteClient(@PathVariable("clientId") int clientId){
        ClientDAO.delete(clientId);
        return "redirect:/manager/allclients";
    }
}
