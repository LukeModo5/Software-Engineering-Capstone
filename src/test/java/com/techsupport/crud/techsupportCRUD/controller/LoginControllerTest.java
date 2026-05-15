package com.techsupport.crud.techsupportCRUD.controller;

import com.techsupport.crud.techsupportCRUD.service.TechnicianService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TechnicianService technicianService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserDetails buildUser(String username, String rawPassword){
        return User.builder()
                .username(username)
                .password(passwordEncoder.encode(rawPassword))
                .roles("TECHNICIAN")
                .build();
    }

    @Test
    void login_withUnknownUsername_redirectsToLoginWithError() throws Exception {
        when(technicianService.loadUserByUsername(anyString()))
                .thenThrow(new UsernameNotFoundException("Account not found"));

        mockMvc.perform(formLogin("/login")
                        .user("username", "unknownUser")
                        .password("password", "anyPassword"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error=true"));
    }
}
