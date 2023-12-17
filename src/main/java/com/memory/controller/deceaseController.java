package com.memory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.memory.model.Decease;
import com.memory.service.DeceaseService;
@Controller
public class deceaseController {
    @Autowired private DeceaseService service;
    @GetMapping(value ="/admin/deceased")
    public String getDeceasePage(@ModelAttribute Decease dease,Model model) {
        model.addAttribute("listOfDeceases",service.getDeceaseList());
        return "admin/deceased";
    }
    public String getAddDeceasePage(@ModelAttribute Decease dease,Model model) {
        service.createDecease(dease);
        return "redirect:/admin/deceased";
    }
    public String getDeleteDeceasePage(@ModelAttribute Decease dease,Model model) {
        service.deleteDecease(dease);
        return "redirect:/admin/deceased";
    }
}
