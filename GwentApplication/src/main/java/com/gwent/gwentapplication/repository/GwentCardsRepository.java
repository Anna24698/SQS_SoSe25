package com.gwent.gwentapplication.repository;

import com.gwent.gwentapplication.dtos.GwentCards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GwentCardsRepository extends JpaRepository<GwentCards, Long> {
    // Hier spezifische Methoden definieren
    List<GwentCards> findGwentCardsByCardCategory(String cardCategory);


    List<GwentCards> findGwentCardsByCardIdIn(List<Long> cardId);

    List<GwentCards> findGwentCardsByAttributeFaction(String attributeFaction);

}
