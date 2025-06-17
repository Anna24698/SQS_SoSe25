/*
import com.gwent.gwentapplication.configuration.CustomAuthenticationSuccessHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class SecurityConfigurationTest {
    @Autowired
    private MockMvc mockMvc;

    // Mocken, damit Spring Security nicht meckert – das sind Abhängigkeiten in der SecurityConfig
    @MockBean
    private CustomAuthenticationSuccessHandler successHandler;

    @Test
    void testPublicEndpointsAccessible() throws Exception {
        String[] publicEndpoints = {"/auth", "/auth/login", "/error", "/buildDeck"};

        for (String endpoint : publicEndpoints) {
            mockMvc.perform(get(endpoint))
                    .andExpect(status().isOk());
        }
    }

    @Test
    void testAuthenticatedEndpoint_UnauthorizedAccess() throws Exception {
        mockMvc.perform(get("/submit-deck"))
                .andExpect(status().is3xxRedirection()) // default redirect to login
                .andExpect(redirectedUrlPattern("**//*auth/login"));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void testAuthenticatedEndpoint_AuthorizedAccess() throws Exception {
        mockMvc.perform(get("/submit-deck"))
                .andExpect(status().isOk());
    }

    @Test
    void testLoginRedirectsToBuildDeckOnSuccess() throws Exception {
        // Simulieren, dass ein Login passiert
        mockMvc.perform(formLogin("/auth/login")
                        .user("username", "testuser")
                        .password("password", "testpassword"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/buildDeck"));
    }

    @Test
    @WithMockUser(username = "testuser")
    void testLogout() throws Exception {
        mockMvc.perform(logout("/auth/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/buildDeck"))
                .andExpect(cookie().maxAge("JSESSIONID", 0)); // sicherstellen, dass Cookie gelöscht wird
    }
}*/
