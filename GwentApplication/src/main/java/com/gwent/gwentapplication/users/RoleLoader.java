package com.gwent.gwentapplication.users;

import com.gwent.gwentapplication.entities.GwentRoles;
import com.gwent.gwentapplication.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleLoader {
    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void ensureUserRole(){
        if (!roleRepository.findByName("USER").isPresent()){
            GwentRoles role = new GwentRoles();
            role.setName("USER");
            roleRepository.save(role);
        }
    }
}
