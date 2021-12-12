package com.example.demoSan.controllers;

import com.example.demoSan.dao.ClientDAO;
import com.example.demoSan.dao.ProcedureDAO;
import com.example.demoSan.dao.TreatmentPurchaseDAO;
import com.example.demoSan.models.Purchase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client")
public class ClientController {

    // страница с кликабельным списком всех клиентов
    @GetMapping("/")
    public String list(Model model){
        model.addAttribute("clients", ClientDAO.clientList());
        return "client/list";
    }

    // карта клиента по ID
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,
                       Model model){
        // вытаскиваем данные о клиенте из БД и передаем под атрибутом client
        model.addAttribute("client", ClientDAO.showClient(id));
        // данные о бронировании клиента. для отображения номера и дат въезда/выезда
        model.addAttribute("reservation", ClientDAO.showReservation(id));
        return "client/show";
    }

    // отображение процедур, полученных клиентом
    @GetMapping("/{id}/myprocedures")
    public String myProcedures(@PathVariable("id") int id,
                               Model model){
        model.addAttribute("clientId", id);
        // получаем купленные процедуры из БД
        model.addAttribute("boughtProcedures", ProcedureDAO.clientBoughtProcedures(id));
        // все процедуры, которые клиент получал
        model.addAttribute("myProcedures", ProcedureDAO.myProcedures(id));
        return "client/myProcedureList";
    }

    // история получения конкретной процедуры клиентом
    @GetMapping("/{id}/history{ProcedureId}")
    public String historyOfProcedureVisit(@PathVariable("id") int clientId,
                                          @PathVariable("ProcedureId") int procedureId,
                                          Model model){
        // данные о получении, находимые по ID клиента и процедуры
        model.addAttribute("treatmentHistory", TreatmentPurchaseDAO.treatmentHistory(clientId, procedureId));
        // передаем данные о процедуре, чтобы отобразить её название
        model.addAttribute("procedure", ProcedureDAO.showProcedure(procedureId));
        return "client/procedureHistory";
    }

    // список всех процедур
    @GetMapping("/{id}/allprocedures")
    public String allProcedures(@PathVariable("id") int id,
                                Model model){
        // процедуры, которые клиент уже купил. для отключения функции покупки этих процедур
        model.addAttribute("myProcedures", ProcedureDAO.clientBoughtProcedures(id));
        // оставшиеся процедуры
        model.addAttribute("restProcedures", ProcedureDAO.restProcedures(id));
        // передаем ID клиента для редиректа на страничку с подтверждением покупки
        model.addAttribute("clientId", id);
        return "client/procedurelist";
    }

    // подтверждение покупки процедуры
    @GetMapping("/{id}/allprocedures/add{ProcedureId}")
    public String addProcedure(Model model,
                               @PathVariable("id") int ClientId,
                               @PathVariable("ProcedureId") int ProcedureId){
        // данные о клиенте для отображения его имени
        model.addAttribute("client", ClientDAO.showClient(ClientId));
        // данные о процедуре для отображения названия
        model.addAttribute("procedure", ProcedureDAO.showProcedure(ProcedureId));
        return "client/addProcedure";
    }

    // ПОСТ-запрос для сохранения покупки
    // с помощью ModelAttribute получаем данные для сохранения покупки в БД
    @PostMapping("/{id}/allprocedures")
    public String savePurchase(@ModelAttribute("procedureId") int ProcedureID,
                                @ModelAttribute("clientId") int ClientID,
                                @PathVariable("id") int id,
                                BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "procedurelist";
        // создаём объект для сохранения его в БД
        Purchase p = new Purchase(ClientID, ProcedureID);
        TreatmentPurchaseDAO.addPurchase(p);
        // перенаправление на чек. (отправит на чек последней покупки)
        return "redirect:/client/"+ id +"/check" + TreatmentPurchaseDAO.showLastPurchase().getPurchaseID();
    }

    // отображение чека с покупки
    @GetMapping("/{id}/check{checkN}")
    public String showCheck(@PathVariable("checkN") int PurchaseId,
                            Model model){
        // создаём объект Покупки, чтобы извлечь ID процедуры и клиента
        Purchase purchase = TreatmentPurchaseDAO.showPurchase(PurchaseId);
        model.addAttribute("client", ClientDAO.showClient(purchase.getClientID()));
        model.addAttribute("procedure", ProcedureDAO.showProcedure(purchase.getProcedureID()));
        model.addAttribute("check", purchase);
        return "client/purchaseCheck";
    }

    // список чеков
    @GetMapping("/{id}/checks")
    public String showAllChecks(@PathVariable("id") int clientId,
                                Model model){
        model.addAttribute("purchases", TreatmentPurchaseDAO.showAllPurchases(clientId));
        model.addAttribute("client", ClientDAO.showClient(clientId));
        return "client/purchasesAll";
    }
}

