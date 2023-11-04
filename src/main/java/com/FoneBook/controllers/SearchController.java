package com.FoneBook.controllers;
//Api to search contacts

import com.FoneBook.Repos.ContactsRepo;
import com.FoneBook.Repos.UserRepo;
import com.FoneBook.models.Contacts;
import com.FoneBook.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ContactsRepo contactsRepo;


    @GetMapping("/search/{query}")
    public ResponseEntity<?> search(@PathVariable("query") String query, Principal principal){

        System.out.println(query);


        Users user=this.userRepo.findUserByName(principal.getName());
        List<Contacts> contacts=this.contactsRepo.findByNameContainingAndUser(query,user);
        return ResponseEntity.ok(contacts);



    }
}
