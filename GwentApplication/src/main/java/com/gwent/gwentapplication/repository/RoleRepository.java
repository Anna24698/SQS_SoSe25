package com.gwent.gwentapplication.repository;

import com.gwent.gwentapplication.dtos.GwentRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<GwentRoles, Long> {


    Optional<GwentRoles> findByName(String name);
}
