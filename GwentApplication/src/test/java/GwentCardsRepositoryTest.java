import com.gwent.gwentapplication.dtos.GwentCards;
import com.gwent.gwentapplication.repository.GwentCardsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
/*
@DataJpaTest
public class GwentCardsRepositoryTest {

    @Autowired
    private GwentCardsRepository gwentCardsRepository;

    @Test
    @DisplayName("findGwentCardsByCardCategory sollte Karten mit der richtigen Kategorie finden")
    void testFindByCardCategory() {
        // Setup
        GwentCards card = new GwentCards();
        card.setCardId(1L);
        card.setCardName("Geralt");
        card.setCardCategory("Witcher");
        card.setAttributeFaction("Neutral");
        gwentCardsRepository.save(card);

        // Execute
        List<GwentCards> result = gwentCardsRepository.findGwentCardsByCardCategory("Witcher");

        // Assert
        //assertTrue(result.hasSize(1)); //TODO
        //assertTrue(result.get(0).getCardName().isEqualTo("Geralt"));
    }

    @Test
    @DisplayName("findGwentCardsByCardIdIn sollte Karten anhand von IDs finden")
    void testFindByCardIdIn() {
        GwentCards card1 = new GwentCards();
        card1.setCardId(2L);
        card1.setCardName("Yennefer");
        gwentCardsRepository.save(card1);

        GwentCards card2 = new GwentCards();
        card2.setCardId(3L);
        card2.setCardName("Triss");
        gwentCardsRepository.save(card2);

        List<GwentCards> result = gwentCardsRepository.findGwentCardsByCardIdIn(List.of(2L, 3L));

        //assertThat(result).hasSize(2);
        //assertThat(result).extracting(GwentCards::getCardName).contains("Yennefer", "Triss");
    }

    @Test
    @DisplayName("findGwentCardsByAttributeFaction sollte Karten mit passender Fraktion finden")
    void testFindByAttributeFaction() {
        GwentCards card = new GwentCards();
        card.setCardId(4L);
        card.setCardName("Ciri");
        card.setAttributeFaction("Scoiatael");
        gwentCardsRepository.save(card);

        List<GwentCards> result = gwentCardsRepository.findGwentCardsByAttributeFaction("Scoiatael");

        //assertThat(result).hasSize(1);
        //assertThat(result.get(0).getCardName()).isEqualTo("Ciri");
    }
}
*/