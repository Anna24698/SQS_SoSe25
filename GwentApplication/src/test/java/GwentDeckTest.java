import com.gwent.gwentapplication.entities.GwentDeck;
import com.gwent.gwentapplication.entities.GwentCards;
import com.gwent.gwentapplication.entities.GwentUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class GwentDeckTest {
    private GwentDeck gwentDeck;
    private GwentUsers user;
    private GwentCards card;

    @BeforeEach
    void setUp() {
        gwentDeck = new GwentDeck();
        user = new GwentUsers();
        card = new GwentCards();
    }

    @Test
    void testIdGetterAndSetter() {
        Long id = 1L;
        gwentDeck.setId(id);
        assertEquals(id, gwentDeck.getId());
    }

    @Test
    void testUserIdGetterAndSetter() {
        user.setId(42L);
        gwentDeck.setUserID(user);
        assertEquals(user, gwentDeck.getUserId());
    }

    @Test
    void testCardIdGetterAndSetter() {
        card.setCardId(100L);
        gwentDeck.setCardId(card);
        assertEquals(card, gwentDeck.getCardId());
    }
}
