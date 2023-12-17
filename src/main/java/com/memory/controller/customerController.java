package com.memory.controller;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.memory.config.EmailSenderServiceConfig;
import com.memory.model.Customer;
import com.memory.model.Decease;
import com.memory.model.OtherServices;
import com.memory.model.Payment;
import com.memory.model.Tomb;
import com.memory.repository.TombRepository;
import com.memory.service.DeceaseService;
import com.memory.service.PaymentServices;
import com.memory.service.TombService;
import com.memory.service.customerServices;

import jakarta.mail.MessagingException;

@Controller
@SessionAttributes({"authUser","customerList"})
public class customerController {
  // Admin privilages
     @Autowired EmailSenderServiceConfig emailsender;
    @Autowired private customerServices customerServices;
    @Autowired private TombService tombServices;
    @Autowired private PaymentServices paymentServices;
    @Autowired private DeceaseService deceaseServices;
    @Autowired private com.memory.service.otherServices optherServices;
    @GetMapping(value="/admin/customer")
    public String getCustomerPageByAdmin(@ModelAttribute Customer customer,Model model) {
       
        model.addAttribute("customer", new Customer());
       return "admin/customer";
    }
    @GetMapping(value="/admin")
    public String getAdminPage(@ModelAttribute Customer customer,Model model) {
      model.addAttribute("customerList", customerServices.getCustomerList());
      model.addAttribute("tombList", tombServices.getTombList());
      model.addAttribute("deceaseList", deceaseServices.getDeceaseList());
      double total=0;
      for(Payment p:paymentServices.getPaymentList())
          {
            total=total+p.getTombId().getCost();
          }
          
      model.addAttribute("totalPayment", total);
       return "admin/index";
    }
    @Autowired TombRepository tombrepo;
    @GetMapping(value="/admin/tomb")
    public String getTombPage(@ModelAttribute Tomb tomb,Model model,@RequestParam(defaultValue = "0")int pageNumber,
    @RequestParam(defaultValue = "1000") int pagesize) {
      Pageable page1=PageRequest.of(pageNumber,pagesize);
      Page<Tomb> page =tombrepo.findAll(page1);
      List<Tomb> result = page.getContent();
      
      model.addAttribute("totalpage",page.getTotalPages());
      model.addAttribute("tombList",result);
      model.addAttribute("customerList", customerServices.getCustomerList());
 
      //model.addAttribute("tomb",new Tomb());
       return "admin/tomb";
    }
    @GetMapping(value="/admin/decease")
    public String getDeceasePage(@ModelAttribute Customer customer,Model model) {
      model.addAttribute("deceaseList", deceaseServices.getDeceaseList());
       return "admin/deceased";
    }
    @GetMapping(value="/admin/maintenance")
    public String getMaintenancePage(@ModelAttribute Customer customer,Model model) {
      model.addAttribute("serviceList",optherServices.getAllOtherServiceslist());
       return "admin/maintenance";
    }
    @GetMapping(value="/admin/payments")
    public String getPaymentPageByAdmin(@ModelAttribute Customer customer,Model model) {
      double amount=0;
      for(Payment payment : paymentServices.getPaymentList())
        amount+=payment.getTombId().getCost();
      model.addAttribute("totalPayment", amount);
      model.addAttribute("paymentList", paymentServices.getPaymentList());
      model.addAttribute("tombList", tombServices.getTombList());
      model.addAttribute("deceaseList", deceaseServices.getDeceaseList());
       return "admin/payment";
    }
    @GetMapping(value="/admin/request")
    public String getRequestPage(@ModelAttribute Customer customer,Model model) {
      model.addAttribute("requestList",optherServices.getAllOtherServiceslist());
       return "admin/request";
    }
    @GetMapping(value="/admin/account")
    public String getAdminAcountPage(@ModelAttribute Customer customer,Model model) {
      model.addAttribute("customer", new Customer());
       return "admin/account";
    }
    @PostMapping(value="/admin/customer/delete")
    public String deletePage(@ModelAttribute Customer customer) {
       customerServices.deleteAccount(customer);
       return "redirect:/admin/customer";
    }
    @PostMapping(value="/admin/account/delete")
    public String deleteAccountPage(@ModelAttribute Customer customer) {
      System.out.println(customer.getId());
       customerServices.deleteAccount(customer);
       return "redirect:/logout?logout";
    }
    
    @PostMapping(value="/admin/tomb")
    public String getAddTombPage(@ModelAttribute Tomb tomb,Model model) {
      tombServices.createTomb(tomb);
       return "redirect:/admin/tomb";
    }
    @GetMapping(value="/admin/userdetail/{userId}")
    public String getUserDetail(@PathVariable int userId,Model model) {
      Customer customer =customerServices.findCustomerById(userId);
      model.addAttribute("customer", customer);
      model.addAttribute("customerPaymentList", paymentServices.getCustomerPaymentList(customer));

      return "admin/userdetail";
    }
    // update admin
    @PostMapping(value = "/admin/updateaccount")
    public String updateAdminAccount(@ModelAttribute Customer customer){
      Customer cs=customerServices.findCustomerById(customer.getId());
      customer.setRole(cs.getRole());
      customerServices.createAccount(customer);
      return "redirect:/admin/account";
    }
 // user privilages
    @GetMapping(value="/customer")
    public String getCustomerPage(@ModelAttribute Customer customer,Model model) {
        model.addAttribute("customer", customerServices.getCustomerList());
       return "customer";
    }
    @PostMapping(value="/customer/register")
    public String createAccount(@ModelAttribute Customer customer) {
        if(customer.getId()==0)
            customer.setRole("USER");
        customer.setPassword(BCrypt.hashpw(customer.getPassword(),BCrypt.gensalt()));
        
        try {
          emailsender.sendSignUpEmail(customer.getEmail(),"Signup Successfully",customer.getNames());
          customerServices.createAccount(customer);
        } catch (MessagingException e) {
          index.Message="Credential Saved Successfully";
          return "redirect:/register";
        }catch (Exception e) {
          index.Message=e.getMessage()+" Wrong Email or phone number please try again";
          return "redirect:/register";
        }
        index.Message=customer.getNames()+" Saved Successfully";
       return "redirect:/";
    }
    // Default success login
    @GetMapping(value = "/success")
  public String getdefaultsuccessurl(Model model)
  {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if(auth!=null && auth.isAuthenticated())
    {
      Collection<? extends GrantedAuthority> authority=auth.getAuthorities();
      String email=auth.getName();
        Customer customer=customerServices.findByEmail(email);
        model.addAttribute("authUser", customer);
      if(authority.contains(new SimpleGrantedAuthority("USER")))
      { 
        return "redirect:/user";
      }else if(authority.contains(new SimpleGrantedAuthority("ADMIN"))){
        return "redirect:/admin";
      }
    }
    return "redirect:/";
  }
  // User Page
  @GetMapping(value="/user")
    public String getUserPage(Model model) {
        model.getAttribute("authUser");
        model.addAttribute("payment",new Payment());
        model.addAttribute("decease",new Decease());
        model.addAttribute("tombList",tombServices.getTombList());
       return "customer/index";
    }
    @GetMapping(value="/user/account")
    public String getUserAccountPage(Model model) {
        model.getAttribute("authUser");
        model.addAttribute("customer", new Customer());
        model.addAttribute("message",index.Message);
        index.Message="";
       return "customer/account";
    }
     @PostMapping(value="/user/account")
    public String getUpdateUserAccountPage(@ModelAttribute Customer customer,Model model)
   {   Customer cr=customerServices.findCustomerById(customer.getId());
       customer.setRole(cr.getRole());
       index.Message="Credential updated successfully";
       customer.setPassword(BCrypt.hashpw(customer.getPassword(),BCrypt.gensalt()));
       customerServices.createAccount(customer);
      model.addAttribute("authUser", customer);

       return "customer/account";
    }
    @GetMapping(value="/user/request")
    public String getUserRequestPage(Model model) {
        model.getAttribute("authUser");
       model.addAttribute("deceaseList",deceaseServices.getDeceaseList());
       return "customer/request";
    }
    @PostMapping(value="/user/pay")
    public String getUserPayment(@RequestParam String dname ,@RequestParam String dgender
    ,@RequestParam Date ddate,@RequestParam  Date ddbDate,@RequestParam int userId
    ,@RequestParam  int tombId) {
      Payment payment=new Payment();
      Customer user=customerServices.findCustomerById(userId);
      Tomb tomb=tombServices.findTombById(tombId);
      Tomb tomb1=new Tomb();
      tomb1=tomb;
      tomb1.setIsbooked(true);
      tombServices.createTomb(tomb1);
      Random random=new Random();
      int rand1=random.nextInt(99999);
      int rand2=random.nextInt(99999);
      int rand3=random.nextInt(99999);
      payment.setPaymentCode(rand1+"-"+rand2+"-"+rand3);
      payment.setTombId(tomb);
      payment.setStatus("Payed");
      payment.setPaymentDate(Date.valueOf(LocalDate.now()));
      payment.setCustomerId(user);
      paymentServices.createPayment(payment);
      Decease decease=new Decease();
      decease.setName(dname);
      decease.setGender(dgender);
      decease.setDob(ddbDate);
      decease.setDeceaseDate(ddate);
      decease.setTombId(tomb);
      deceaseServices.createDecease(decease);
       return "redirect:/user";
    } 
    @PostMapping(value="/user/request")
    public String createUserRequestPage(@RequestParam int userId,@RequestParam String serviceName,@RequestParam int tombId
    ,@RequestParam Date dateOfService,Model model) {
  OtherServices otherServices = new OtherServices();
  otherServices.setServiceName(serviceName);
  Tomb tomb=tombServices.findTombById(tombId);
  Payment payment=new Payment();
  Random random = new Random();
  int p1=random.nextInt(999999);
  int p2=random.nextInt(999999);
  int p3=random.nextInt(999999);
  payment.setPaymentCode(p1+"-"+p2+"-"+p3);
  Customer customer =customerServices.findCustomerById(userId);
  if (tomb==null)
  {System.out.println("Not found");
    return "redirect:/user/request";
  }else{
    payment.setTombId(tomb);
    payment.setStatus("payed");
    payment.setPaymentDate(Date.valueOf(LocalDate.now()));
    payment.setCustomerId(customer);
    paymentServices.createPayment(payment);
    otherServices.setTombId(tomb);
    otherServices.setPaymentId(payment);
    otherServices.setDateOfService(dateOfService);
    otherServices.setCreatedDate(Date.valueOf(LocalDate.now()));
    optherServices.createOtherservice(otherServices);
    return "redirect:/user/request";
  }   
 } 
//  delete customer account
 @PostMapping(value="/customer/delete")
    public String deleteAccountByCustomerPage(@ModelAttribute Customer customer) {
       customerServices.deleteAccount(customer);
       index.Message="Account deleted successfully";
       return "redirect:/logout?logout";
    }
}
