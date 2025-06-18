package UnitTests;

import com.gwent.gwentapplication.configuration.CustomAuthenticationSuccessHandler;
import com.gwent.gwentapplication.entities.GwentUsers;
import com.gwent.gwentapplication.repository.GwentUsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.core.Authentication;

import java.util.Optional;

import static org.mockito.Mockito.*;
public class CustomAuthenticationSuccessHandlerTest {
    @InjectMocks
    private CustomAuthenticationSuccessHandler handler;

    @Mock
    private GwentUsersRepository userRepository;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRedirectToBuildDeck_whenUserExists() throws Exception {
        // Arrange
        String username = "testuser";
        GwentUsers user = new GwentUsers();
        user.setUsername(username);

        when(authentication.getName()).thenReturn(username);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // Act
        handler.onAuthenticationSuccess(request, response, authentication);

        // Assert
        verify(response).sendRedirect("/buildDeck");
    }

    @Test
    void testRedirectToError_whenUserDoesNotExist() throws Exception {
        // Arrange
        String username = "unknownuser";

        when(authentication.getName()).thenReturn(username);
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act
        handler.onAuthenticationSuccess(request, response, authentication);

        // Assert
        verify(response).sendRedirect("/error");
    }
}
