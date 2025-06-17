package com.gwent.gwentapplication.users;


import com.gwent.gwentapplication.entities.GwentRoles;
import com.gwent.gwentapplication.entities.GwentUsers;
import com.gwent.gwentapplication.repository.GwentUsersRepository;
import com.gwent.gwentapplication.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;


@Controller
@RequestMapping("/auth")
public class UserController {

    private final GwentUsersRepository gwentUsersRepository;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(GwentUsersRepository gwentUsersRepository, AuthenticationManager authenticationManager,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder
                          //, JWTGenerator jwtGenerator
                          ) {
        this.gwentUsersRepository = gwentUsersRepository;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String register(Model model) {
        System.out.println("Register get");
        model.addAttribute("gwentuser", new GwentUsers());
        return "registerUser";
    }
    @PostMapping("/register")
    public //ResponseEntity<Void>
            String registerPost (@ModelAttribute GwentUsers user, Model model){
        System.out.println("Register post");
        if (Boolean.TRUE.equals(gwentUsersRepository.existsByUsername(user.getUsername()))){
            //return new ResponseEntity<>("Username is taken", HttpStatus.BAD_REQUEST);
            //return ResponseEntity.status(HttpStatus.).header(HttpHeaders.LOCATION, "/buildDeck").build();
            model.addAttribute("errorMessage", "Benutzer existiert schon");
            model.addAttribute("gwentuser", new GwentUsers());
            return "registerUser";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        GwentRoles roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));
        Long maxId = gwentUsersRepository.findMaxId();
        if (maxId == null){
            maxId = Integer.toUnsignedLong(0);
        }
        user.setId(maxId + 1);
        gwentUsersRepository.save(user);
        //return new ResponseEntity<>("User registered", HttpStatus.OK);
        //return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION, "/buildDeck").build();
                return "redirect:/login";
    }
    @GetMapping("/login")
    public String login(Model model){
        //System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated() + SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("gwentuser", new GwentUsers());
        return "loginUser";
    }
    @PostMapping("/login")
    public ResponseEntity<Void> loginPost (@ModelAttribute GwentUsers user){
        System.out.println("Login post");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);


        return ResponseEntity
                .status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, "/buildDeck")
                .build();
    }




}
