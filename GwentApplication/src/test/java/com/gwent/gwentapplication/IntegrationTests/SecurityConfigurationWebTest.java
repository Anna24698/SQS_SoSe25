package com.gwent.gwentapplication.IntegrationTests;

import com.gwent.gwentapplication.GwentApplication;
import com.gwent.gwentapplication.cards.GwentCardsController;
import com.gwent.gwentapplication.configuration.CustomAuthenticationSuccessHandler;
import com.gwent.gwentapplication.configuration.SecurityConfiguration;
import com.gwent.gwentapplication.users.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@WebMvcTest(controllers = {GwentCardsController.class, UserController.class})
//        @Import(SecurityConfiguration.class)

@SpringBootTest (classes = GwentApplication.class)
@AutoConfigureMockMvc
//@ContextConfiguration(classes = GwentApplication.class)
//@SpringBootTest(classes = SecurityConfiguration.class)
@ActiveProfiles("ci")

public class SecurityConfigurationWebTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomAuthenticationSuccessHandler successHandler;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    // Wird benötigt, weil dein SecurityConfig einen CustomAuthenticationSuccessHandler verwendet

    @Test
    void testPublicEndpointsAccessibleWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/auth/register"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/auth/login"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/buildDeck"))
                .andExpect(status().isOk());

      //  mockMvc.perform(get("/error"))
      //          .andExpect(status().isOk());
    }

    @Test
    void testLoginPageAccessible() throws Exception {
        mockMvc.perform(get("/auth/login"))
                .andExpect(status().isOk());
    }

    @Test
    void testProtectedPostEndpointDeniedWithoutAuth() throws Exception {
        mockMvc.perform(post("/submit-deck"))
                .andExpect(status().isForbidden());
    }

 /*   @Test
    @WithMockUser (roles = "USER")
    void testProtectedPostEndpointAccessibleWithAuth() throws Exception {
        mockMvc.perform(post("/submit-deck?deck=\"/buildDeck2?id=112108\",\"/buildDeck2?id=112112\",\"/buildDeck2?id=112202\",\"/buildDeck2?id=112209\",\"/buildDeck2?id=112210\"")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isOk());
    }*/

    @Test
    @WithMockUser
    void testLogoutRedirectsToBuildDeck() throws Exception {
        mockMvc.perform(post("/auth/logout")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())) // CSRF für POST erforderlich
                .andExpect(status().isNoContent());
    }


}
