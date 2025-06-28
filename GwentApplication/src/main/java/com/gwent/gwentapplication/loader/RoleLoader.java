package com.gwent.gwentapplication.loader;

import com.gwent.gwentapplication.entities.GwentRoles;
import com.gwent.gwentapplication.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleLoader {
    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void ensureUserRole(){

        List<GwentRoles> roles = roleRepository.findAllByName("USER");

        if (roles.isEmpty()) {
            // Falls noch keine "USER"-Rolle vorhanden ist, eine anlegen
            GwentRoles role = new GwentRoles();
            role.setName("USER");
            roleRepository.save(role);
        } else if (roles.size() > 1) {
            // Mehrere "USER"-Rollen gefunden -> alle bis auf eine löschen
            GwentRoles keepRole = roles.get(0); // eine behalten
            List<GwentRoles> duplicates = roles.subList(1, roles.size()); // rest löschen
            roleRepository.deleteAll(duplicates);
        }

    }
}
