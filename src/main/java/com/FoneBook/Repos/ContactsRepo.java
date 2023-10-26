package com.FoneBook.Repos;

import com.FoneBook.models.Contacts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactsRepo extends JpaRepository<Contacts,Integer> {
    //pagination
    @Query("from Contacts as c where c.user.id=:userId")
    public Page<Contacts> findContactsById(@Param("userId")int userId, Pageable pageable);
}
