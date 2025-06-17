package com.gwent.gwentapplication.repository;

import com.gwent.gwentapplication.dtos.GwentDeck;
import com.gwent.gwentapplication.dtos.GwentUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GwentDeckRepository extends JpaRepository<GwentDeck, Long> {


    List<GwentDeck> findGwentDecksByUserId(GwentUsers userId);


    //void deleteGwentDeckByUserId(GwentUsers userId);

    @Query("SELECT MAX(u.id) FROM GwentDeck u")
    Long findMaxId();
}
