package com.techsupport.crud.techsupportCRUD.controller;

import com.techsupport.crud.techsupportCRUD.service.RequestService;
import com.techsupport.crud.techsupportCRUD.service.TechnicianService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CreateRequestSuccessTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RequestService requestService;

    @MockitoBean
    private TechnicianService technicianService;

    @Test
    void saveRequestSuccess() throws Exception {
        when(requestService.saveRequest(any())).thenReturn(true);

        mockMvc.perform(post("/saveRequest")
                        .param("name", "John Doe")
                        .param("phoneNumber", "1234567890")
                        .param("email", "johndoe@gmail.com")
                        .param("deviceType", "Computer")
                        .param("issueDescription", "My screen is flickering")
                        .param("urgent", "false"))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/createRequest"))

                .andExpect(flash().attribute("message", "Request Saved"));
    }
}
