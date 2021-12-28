package com.example.demoSan.controllers;

import com.example.demoSan.dao.*;
import com.example.demoSan.models.Purchase;
import com.example.demoSan.security.IUserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private IUserId userId;

    // карта клиента по ID
    @GetMapping("")
    public String showMenu(Model model){
        // вытаскиваем данные о клиенте из БД и передаем под атрибутом client
        model.addAttribute("client", ClientDAO.showClient(userId.get()));
        // данные о бронировании клиента. для отображения номера и дат въезда/выезда
        model.addAttribute("reservation", ClientDAO.showReservation(userId.get()));
        return "client/clientMenu";
    }

    @GetMapping("/mycard")
    public String showMyCard(Model model){
        // вытаскиваем данные о клиенте из БД и передаем под атрибутом client
        model.addAttribute("client", ClientDAO.showClient(userId.get()));
        // данные о бронировании клиента. для отображения номера и дат въезда/выезда
        model.addAttribute("reservation", ClientDAO.showReservation(userId.get()));
        return "client/personalCard";
    }

    // отображение процедур, полученных клиентом
    @GetMapping("/myprocedures")
    public String showMyProcedures(Model model){
        model.addAttribute("client", ClientDAO.showClient(userId.get()));
        model.addAttribute("clientId", userId.get());
        // получаем купленные процедуры из БД
        model.addAttribute("boughtProcedures", ProcedureDAO.clientBoughtProcedures(userId.get()));
        // все процедуры, которые клиент получал
        model.addAttribute("myProcedures", ProcedureDAO.myProcedures(userId.get()));
        return "client/myProcedureList";
    }

    // история получения всех процедур клиентом
    @GetMapping("/showHistory")
    public String showHistory(Model model){
        // данные о получении, находимые по ID клиента и процедуры
//        model.addAttribute("treatmentHistory", TreatmentPurchaseDAO.treatmentHistory(userId.get(), procedureId));
        // передаем данные о процедуре, чтобы отобразить её название
//        model.addAttribute("procedure", ProcedureDAO.showProcedure(procedureId));
        model.addAttribute("client", ClientDAO.showClient(userId.get()));
        return "client/procedureHistoryOfOne";
    }

    // история получения конкретной процедуры клиентом
    @GetMapping("/history{ProcedureId}")
    public String showHistoryOfTreatment(@PathVariable("ProcedureId") int procedureId,
                                          Model model){
        model.addAttribute("client", ClientDAO.showClient(userId.get()));
        // данные о получении, находимые по ID клиента и процедуры
        model.addAttribute("treatmentHistory", TreatmentPurchaseDAO.treatmentHistory(userId.get(), procedureId));
        // передаем данные о процедуре, чтобы отобразить её название
        model.addAttribute("procedure", ProcedureDAO.showProcedure(procedureId));
        return "client/procedureHistoryOfOne";
    }

    // список всех процедур
    @GetMapping("/allprocedures")
    public String showAllProcedures(Model model){
        model.addAttribute("client", ClientDAO.showClient(userId.get()));
        // процедуры, которые клиент уже купил. для отключения функции покупки этих процедур
        model.addAttribute("myProcedures", ProcedureDAO.clientBoughtProcedures(userId.get()));
        // оставшиеся процедуры
        model.addAttribute("restProcedures", ProcedureDAO.restProcedures(userId.get()));
        // передаем ID клиента для редиректа на страничку с подтверждением покупки
        model.addAttribute("clientId", userId.get());
        return "client/procedurelist";
    }

    // подтверждение покупки процедуры
    @GetMapping("/allprocedures/add{ProcedureId}")
    public String buyProcedure(Model model,
                               @PathVariable("ProcedureId") int ProcedureId){
        // данные о клиенте для отображения его имени
        model.addAttribute("client", ClientDAO.showClient(userId.get()));
        // данные о процедуре для отображения названия
        model.addAttribute("procedure", ProcedureDAO.showProcedure(ProcedureId));
        return "client/addProcedure";
    }

    // ПОСТ-запрос для сохранения покупки
    // с помощью ModelAttribute получаем данные для сохранения покупки в БД
    @PostMapping("/allprocedures")
    public String savePurchase(@ModelAttribute("procedureId") int ProcedureID,
                                @ModelAttribute("clientId") int ClientID,
                                Model model,
                                BindingResult bindingResult){
        model.addAttribute("client", ClientDAO.showClient(userId.get()));
        if(bindingResult.hasErrors())
            return "procedurelist";
        // создаём объект для сохранения его в БД
        Purchase p = new Purchase(ClientID, ProcedureID);
        TreatmentPurchaseDAO.addPurchase(p);
        // перенаправление на чек. (отправит на чек последней покупки)
        return "redirect:/client/check" + TreatmentPurchaseDAO.showLastPurchase().getPurchaseID();
    }

    // отображение чека с покупки
    @GetMapping("/check{checkN}")
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
    @GetMapping("/checks")
    public String showAllChecks(Model model){
        model.addAttribute("purchases", TreatmentPurchaseDAO.showAllPurchases(userId.get()));
        model.addAttribute("client", ClientDAO.showClient(userId.get()));
        return "client/purchasesAll";
    }
}

