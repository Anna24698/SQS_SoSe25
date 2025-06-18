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
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/*
@WebMvcTest(UserController.class)
@SpringBootTest(classes = GwentApplication.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GwentUsersRepository gwentUsersRepository;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    private GwentUsers mockUser;

    @BeforeEach
    void setUp() {
        mockUser = new GwentUsers();
        mockUser.setId(1L);
        mockUser.setUsername("testuser");
        mockUser.setPassword("testpass");
    }

    @Test
    void testGetRegisterPage() throws Exception {
        mockMvc.perform(get("/auth/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("registerUser"))
                .andExpect(model().attributeExists("gwentuser"));
    }

    @Test
    void testPostRegisterNewUser() throws Exception {
        when(gwentUsersRepository.existsByUsername(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedpass");
        when(roleRepository.findByName("USER")).thenReturn(Optional.of(new GwentRoles()));
        when(gwentUsersRepository.findMaxId()).thenReturn(1L);

        mockMvc.perform(post("/auth/register")
                        .param("username", "newuser")
                        .param("password", "newpass"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

        verify(gwentUsersRepository).save(any(GwentUsers.class));
    }

    @Test
    void testPostRegisterExistingUser() throws Exception {
        when(gwentUsersRepository.existsByUsername(anyString())).thenReturn(true);

        mockMvc.perform(post("/auth/register")
                        .param("username", "existinguser")
                        .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(view().name("registerUser"))
                .andExpect(model().attributeExists("errorMessage"));
    }

    @Test
    void testGetLoginPage() throws Exception {
        mockMvc.perform(get("/auth/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginUser"))
                .andExpect(model().attributeExists("gwentuser"));
    }

    @Test
    void testPostLogin() throws Exception {
        Authentication auth = mock(Authentication.class);
        when(authenticationManager.authenticate(any())).thenReturn(auth);

        mockMvc.perform(post("/auth/login")
                        .param("username", "testuser")
                        .param("password", "testpass")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/buildDeck"));
    }
}
*//*

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
        //usersRepository.deleteById(999L);
        //roleRepository.delete;

        GwentRoles role = new GwentRoles();
        role.setName("USER");
        roleRepository.save(role);

        GwentUsers user = new GwentUsers();
        user.setUsername("testuser");
        user.setPassword(passwordEncoder.encode("password"));
        user.setRoles(Collections.singletonList(role));
        user.setId(999L);
        usersRepository.save(user);
    }

    @Test
    @DisplayName("GET /auth/register liefert Registrierungsformular")
    void shouldDisplayRegisterForm() throws Exception {
        mockMvc.perform(get("/auth/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("registerUser"))
                .andExpect(content().string(containsString("Registrieren")));
    }
/*
    @Test
    @DisplayName("POST /auth/register – Erfolgreiche Registrierung")
    void shouldRegisterUserSuccessfully() throws Exception {
        mockMvc.perform(post("/auth/register")
                        .param("username", "newuser")
                        .param("password", "securepass")
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

        // Sicherstellen, dass Nutzer in DB ist
        assert usersRepository.existsByUsername("newuser");
    }

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
                .andExpect(status().isOk()) // bleibt auf Formularseite
                .andExpect(view().name("registerUser"))
                .andExpect(content().string(containsString("Benutzer existiert schon")));
    }
*/
//}