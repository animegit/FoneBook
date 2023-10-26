package com.FoneBook.controllers;

import com.FoneBook.Helper.Message;
import com.FoneBook.Repos.ContactsRepo;
import com.FoneBook.Repos.UserRepo;
import com.FoneBook.models.Contacts;
import com.FoneBook.models.Users;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ContactsRepo contactsRepo;



    @ModelAttribute
    public void commonData(Model m,Principal principal){
        String username=principal.getName();

        Users users=userRepo.findUserByName(username);

        m.addAttribute("user",users);

    }
@RequestMapping("/index")
    public String dashboard(Model m, Principal principal){

return "user_dashboard";
    }

    @GetMapping("/add-contacts")
    public String addcontacts(Model m){

        m.addAttribute("title","AddContacts");
        m.addAttribute("contact",new Contacts());
        return "contactform";
    }
    @PostMapping("/process-contact")
    public String processContact(@ModelAttribute Contacts contacts, @RequestParam("profileImg")MultipartFile file, HttpSession session, Principal principal){


        try{
        if(file.isEmpty()){

            System.out.println("Image is not added");
            contacts.setImgurl("default.png");

        }
        else{

            //Image will be added to build folder which is test
            contacts.setImgurl(file.getOriginalFilename());
            File imgfile=new ClassPathResource("/static/Img").getFile();
            Path path= Paths.get(imgfile.getAbsolutePath()+File.separator+file.getOriginalFilename());
            Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Img is added successfully");


            //Print message
            session.setAttribute("message",new Message("Contact Added Successfully!!","success"));




        }
        String name=principal.getName();
        Users user=userRepo.findUserByName(name);
        contacts.setUser(user);
        user.getContacts().add(contacts);
        this.userRepo.save(user);}
        catch (Exception e){
            System.out.println(e);

            //Print message
                session.setAttribute("message",new Message("Something Went wrong!!","danger"));

        }


        return "contactform";
    }
    //Handler to show contacts
    @GetMapping("/show-contacts/{page}")
    public String showContacts(@PathVariable("page") Integer page,Model m,Principal principal){
        String username=principal.getName();
        Users user=userRepo.findUserByName(username);
        Pageable pageable= PageRequest.of(page,5);

        Page<Contacts> contacts=contactsRepo.findContactsById(user.getId(),pageable);
        m.addAttribute("contacts",contacts);
        m.addAttribute("currentPage",page);
        m.addAttribute("totalPages",contacts.getTotalPages());

        m.addAttribute("title","Contacts");
        return "show_contacts";

    }
    @RequestMapping("{cid}/contact")
    public String showContact(@PathVariable("cid") Integer cid,Model m,Principal principal){

        Optional<Contacts> contactsOptional=this.contactsRepo.findById(cid);
        Contacts contacts=contactsOptional.get();
        String userName=principal.getName();
        Users user=this.userRepo.findUserByName(userName);
        if(user.getId()==contacts.getUser().getId()){
            m.addAttribute("contact",contacts);
            m.addAttribute("title",contacts.getName());
        }




        return "contact_details";
    }

    @GetMapping("/delete/{cid}")
    public String DeleteContact(@PathVariable("cid") Integer cid,Principal principal,HttpSession session){
        Contacts contact=this.contactsRepo.findById(cid).get();
        contact.setUser(null);
        this.contactsRepo.delete(contact);
        session.setAttribute("message",new Message("Contact Deleted Succesfully!!","success"));

        return "redirect:/user/show-contacts/0";
    }

    @PostMapping("/update-contact/{cid}")
    public String UpdateContact(@PathVariable("cid")Integer cid,Model m){

        m.addAttribute("title","Update");
        this.contactsRepo.findById(cid);
        Contacts contact=this.contactsRepo.findById(cid).get();
        m.addAttribute("contact",contact);
        return "update_contact";
    }
}
