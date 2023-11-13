package com.FoneBook.controllers;

import com.FoneBook.services.EmailServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;


@Controller

public class ForgotController {

    Random random=new Random(1000);
    @Autowired
    private EmailServices emailServices;


    @RequestMapping("/forgot")
    public String forgot(){
        return "forgot";

    }

    @PostMapping("/send-otp")
    public String otp(@RequestParam("email") String email, HttpSession session){

        int otp=random.nextInt(9999);
        System.out.println(otp);

        String subject="OTP FROM FONEBOOK";
        String message="Otp is "+otp;
        String to=email;
        boolean flag=this.emailServices.sendEmail(subject,message,to);
        if(flag){

            return "verify-otp";
        }

               else{
                   session.setAttribute("message","Check your mail");
                   return "forgot";


        }
    }
}
