package com.gwent.gwentapplication.repository;




import com.gwent.gwentapplication.dtos.GwentUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GwentUsersRepository extends JpaRepository<GwentUsers, Long> {
        // Hier spezifische Methoden definieren


         Optional<GwentUsers> findByUsername(String username);

         Boolean existsByUsername(String username);



    @Query("SELECT MAX(u.id) FROM GwentUsers u")
    Long findMaxId();
}
