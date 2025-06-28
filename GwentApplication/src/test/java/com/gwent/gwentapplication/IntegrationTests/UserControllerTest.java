package com.gwent.gwentapplication.IntegrationTests;

import com.gwent.gwentapplication.GwentApplication;
import com.gwent.gwentapplication.entities.GwentRoles;
import com.gwent.gwentapplication.entities.GwentUsers;
import com.gwent.gwentapplication.repository.GwentUsersRepository;
import com.gwent.gwentapplication.repository.RoleRepository;
import com.gwent.gwentapplication.users.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(classes = GwentApplication.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GwentUsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {


        Optional<GwentRoles> existingRole = roleRepository.findByName("USER");
        GwentRoles role;

        if (existingRole.isPresent()) {
            role = existingRole.get();
        } else {
            role = new GwentRoles();
            role.setName("USER");
            role = roleRepository.save(role);
        }


        Optional<GwentUsers> existingUser = usersRepository.findByUsername("testuser");
        if (existingUser.isEmpty()) {
            GwentUsers user = new GwentUsers();
            user.setUsername("testuser");
            user.setPassword(passwordEncoder.encode("password"));
            user.setRoles(Collections.singletonList(role));
            user.setId(10L);
            usersRepository.save(user);
        }
    }

    @Test
    @DisplayName("GET /auth/register liefert Registrierungsformular")
    void shouldDisplayRegisterForm() throws Exception {
        mockMvc.perform(get("/auth/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("registerUser"))
                .andExpect(content().string(containsString("Registrieren")));
    }

    @Transactional
    @Rollback
    @Test
    @DisplayName("POST /auth/register – Erfolgreiche Registrierung")
    void shouldRegisterUserSuccessfully() throws Exception {
        mockMvc.perform(post("/auth/register")
                        .param("username", "newuser")
                        .param("password", "securepass")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/login"));

        // Sicherstellen, dass Nutzer in DB ist
        assert usersRepository.existsByUsername("newuser");

    }

    @Transactional
    @Rollback
    @Test
    @DisplayName("POST /auth/register – Fehler bei vorhandenem Benutzernamen")
    void shouldFailIfUserAlreadyExists() throws Exception {
        // User vorbereiten
        GwentRoles role = roleRepository.findByName("USER").orElseThrow();
        var user = new com.gwent.gwentapplication.entities.GwentUsers();
        user.setUsername("duplicate");
        user.setPassword(passwordEncoder.encode("pw"));
        user.setRoles(java.util.Collections.singletonList(role));
        user.setId(1L);
        usersRepository.save(user);

        mockMvc.perform(post("/auth/register")
                        .param("username", "duplicate")
                        .param("password", "anotherpw")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isOk()) // bleibt auf Formularseite
                .andExpect(view().name("registerUser"))
                .andExpect(content().string(containsString("Benutzer existiert schon")));
    }
}