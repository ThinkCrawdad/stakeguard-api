package com.stakeguard.api.controllers;

import com.stakeguard.api.models.User;
import com.stakeguard.api.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WaitlistController.class)
public class WaitlistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testJoinWaitlist() throws Exception {
        String email = "test@example.com";

        mockMvc.perform(post("/api/waitlist/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(email))
                .andExpect(status().isOk())
                .andExpect(content().string("¡Gracias! Ya estás en la lista de espera de StakeGuard."));

        verify(userRepository).save(any(User.class));
    }
}
