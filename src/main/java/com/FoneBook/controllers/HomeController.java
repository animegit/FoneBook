package com.FoneBook.controllers;

import com.FoneBook.Helper.Message;
import com.FoneBook.Repos.UserRepo;
import com.FoneBook.models.Users;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class HomeController {

    @Autowired
public UserRepo userrepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    // handler for homepage
    @RequestMapping("/")
    public String home(Model m){
        m.addAttribute("title","Home");
        return "home";
    }
    // handler for about
    @RequestMapping("/about")
    public String about(Model m){
        m.addAttribute("title","About");
        return "about";
    }
    // handler for signup
    @RequestMapping("/signup")
    public String SignUp(Model m){
        m.addAttribute("title","Register");
        m.addAttribute("user",new Users());
        return "Signup";
    }
    @PostMapping("/do_register")
    public String register(@Valid @ModelAttribute("user") Users user , BindingResult bindingResult, @RequestParam(value = "agreement",defaultValue = "false") boolean agreement, Model m, HttpSession session) throws Exception {
       try{

           if(bindingResult.hasErrors()){
               System.out.println("Error "+ bindingResult.toString());
               m.addAttribute("user",user);
               return "Signup";
           }
           if(!agreement){
               throw new Exception("You have not agrees terms and conditons");
           }

           user.setRole("ROLE_USER");
           user.setEnabled(true);
           user.setImgurl("default.png");
           user.setPassword(passwordEncoder.encode(user.getPassword()));
           Users res=userrepo.save(user);
           m.addAttribute("user",new Users());
           session.setAttribute("message",new Message("Successfully Registered","alert-success"));


           return "Signup";
       }catch (Exception e){
           e.printStackTrace();
           m.addAttribute("user",user);
           session.setAttribute("message",new Message("Something went wrong!!!!"+e.getMessage(),"alert-danger"));
           return "Signup";
       }
    }
// handler for login
    @RequestMapping("/login")
    public String login(Model m){
        m.addAttribute("title","Login");

        return "login";
    }
}
