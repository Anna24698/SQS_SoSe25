package com.gwent.gwentapplication.configuration;


import com.gwent.gwentapplication.entities.GwentUsers;
import com.gwent.gwentapplication.repository.GwentUsersRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private GwentUsersRepository userRepository; // oder dein Service, der UserDetails zurückgibt

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String username = authentication.getName();
        Optional<GwentUsers> user = userRepository.findByUsername(username); // oder wie dein User geladen wird

        if (user.isPresent()) {
            response.sendRedirect("/buildDeck");
        } else {
            response.sendRedirect("/error"); // Fallback
        }
    }
}

