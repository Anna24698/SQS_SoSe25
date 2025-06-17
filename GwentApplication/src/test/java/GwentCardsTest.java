import com.gwent.gwentapplication.cards.GwentCards;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GwentCardsTest {
    @Test
    void testGettersAndSetters() {
        GwentCards card = new GwentCards();

        // Werte setzen
        card.setCardId(1L);
        card.setArtId(2L);
        card.setAttributeSet("Set1");
        card.setAttributeType("Unit");
        card.setAttributeColor("Gold");
        card.setAttributePower("5");
        card.setAttributeReach("Melee");
        card.setAttributeRarity("Legendary");
        card.setAttributeFaction("Northern Realms");
        card.setAttributeProvision(10);
        card.setCardName("Geralt");
        card.setCardCategory("Witcher");
        card.setCardAbility("Deal 3 damage");
        card.setCardKeyword("Keyword1 Keyword2");

        // Werte prüfen
        assertEquals(1L, card.getCardId());
        assertEquals(2L, card.getArtId());
        assertEquals("Set1", card.getAttributeSet());
        assertEquals("Unit", card.getAttributeType());
        assertEquals("Gold", card.getAttributeColor());
        assertEquals("5", card.getAttributePower());
        assertEquals("Melee", card.getAttributeReach());
        assertEquals("Legendary", card.getAttributeRarity());
        assertEquals("Northern Realms", card.getAttributeFaction());
        assertEquals(10, card.getAttributeProvision());
        assertEquals("Geralt", card.getCardName());
        assertEquals("Witcher", card.getCardCategory());
        assertEquals("Deal 3 damage", card.getCardAbility());
        assertEquals("Keyword1 Keyword2", card.getCardKeyword());
    }
}
