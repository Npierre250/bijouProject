package com.memory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.memory.model.Customer;

@Controller
public class index {
  public static String Message = "";
    @GetMapping(value ="/")
  private String getIndexPage(@ModelAttribute Customer customer,Model model)  
  {model.addAttribute("message",Message);
  Message="";
    return "index";
  }
  @GetMapping(value ="/register")
  private String getRegisterPage(Model model)  
  {model.addAttribute("customer", new Customer());
  model.addAttribute("message", Message);
  Message="";
    return "register";
  }
  @GetMapping(value ="/login")
  private String getLogInPage(@ModelAttribute Customer customer,Model model)  
  {
    return "index";
  }
  @GetMapping(value ="/logout")
  private String getlogOutPage(@ModelAttribute Customer customer,Model model)  
  {
    return "index";
  }
  
}
