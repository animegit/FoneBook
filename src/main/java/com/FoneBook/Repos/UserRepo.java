package com.FoneBook.Repos;

import com.FoneBook.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository< Users,Integer>
{
    @Query("select u from Users u where u.email=:email")
    public Users getUserByUserName(@Param("email") String email);


    public Users findUserByName(String username);

}
