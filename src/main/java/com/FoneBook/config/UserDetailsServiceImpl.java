package com.FoneBook.config;


import com.FoneBook.Repos.UserRepo;
import com.FoneBook.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    public UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users=userRepo.getUserByUserName(username);

        if(users==null){

            throw new UsernameNotFoundException("Could not find username");
        }
CustomUserDetails customUserDetails=new CustomUserDetails(users);
        return customUserDetails;
    }
}
