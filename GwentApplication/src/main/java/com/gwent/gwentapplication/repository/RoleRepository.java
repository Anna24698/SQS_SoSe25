package com.gwent.gwentapplication.repository;

import com.gwent.gwentapplication.entities.GwentRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<GwentRoles, Long> {


    Optional<GwentRoles> findByName(String name);
    List<GwentRoles> findAllByName(String name);


}
