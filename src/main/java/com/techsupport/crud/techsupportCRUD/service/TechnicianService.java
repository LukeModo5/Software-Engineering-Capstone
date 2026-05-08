package com.techsupport.crud.techsupportCRUD.service;

import com.techsupport.crud.techsupportCRUD.model.TechnicianAccount;
import com.techsupport.crud.techsupportCRUD.repo.TechnicianRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TechnicianService implements UserDetailsService {

    @Autowired
    TechnicianRepo technicianRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        TechnicianAccount account = technicianRepo.findByUsername(username);

        if(account == null){
            throw new UsernameNotFoundException("Account not found: " + username);
        }

        return User.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .roles("TECHNICIAN")
                .build();

    }

}
