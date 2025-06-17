import com.gwent.gwentapplication.cards.CardLoader;
import com.gwent.gwentapplication.dtos.GwentCards;
import com.gwent.gwentapplication.repository.GwentCardsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class CardLoaderTest {

    @InjectMocks
    private CardLoader cardLoader;

    @Mock
    private GwentCardsRepository gwentCardsRepository;

    @Mock
    private GwentCards mockCard;

    @BeforeEach
    void setup() {
        // Du kannst hier allgemeine Vorbereitungen treffen
    }

    @Test
    void testMergeImages_shouldReturnBufferedImage() throws IOException {
        // Arrange
        String url1 = "https://example.com/image1.png";
        String url2 = "https://example.com/image2.png";
        List<String> urls = List.of(url1, url2);

        // Damit ImageIO.read nicht wirklich ausgeführt wird, sondern ein Dummy zurückgibt:
        BufferedImage dummyImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        try (MockedStatic<ImageIO> mockedImageIO = mockStatic(ImageIO.class)) {
            mockedImageIO.when(() -> ImageIO.read(new URL(url1)))
                    .thenReturn(dummyImage);
            mockedImageIO.when(() -> ImageIO.read(new URL(url2)))
                    .thenReturn(dummyImage);

            // Act
            BufferedImage result = cardLoader.mergeImages(urls);

            // Assert
            assertNotNull(result);
            assertEquals(100, result.getWidth());
            assertEquals(100, result.getHeight());
        }
    }

    @Test
    void testCollectURLs_withUnitCard_shouldReturnProperUrls() {
        // Arrange
        when(gwentCardsRepository.getReferenceById(1L)).thenReturn(mockCard);
        when(mockCard.getAttributeType()).thenReturn("Unit");
        when(mockCard.getArtId()).thenReturn(123L);
        when(mockCard.getAttributeColor()).thenReturn("Gold");
        when(mockCard.getAttributeProvision()).thenReturn(10);
        when(mockCard.getAttributeFaction()).thenReturn("Northern Realms");
        when(mockCard.getAttributePower()).thenReturn("5");
        when(mockCard.getAttributeRarity()).thenReturn("Legendary");

        // Act
        List<String> urls = cardLoader.collectURLs("1");

        // Assert
        assertNotNull(urls);
        assertFalse(urls.isEmpty());
        // Prüfe ein Beispiel-URL-Fragment
        assertTrue(urls.stream().anyMatch(url -> url.contains("provision_10.png")));
    }

    @Test
    void testCollectURLs_withSpecialCard_shouldReturnProperUrls() {
        // Arrange
        when(gwentCardsRepository.getReferenceById(2L)).thenReturn(mockCard);
        when(mockCard.getAttributeType()).thenReturn("Special");
        when(mockCard.getArtId()).thenReturn(456L);
        when(mockCard.getAttributeColor()).thenReturn("Silver");
        when(mockCard.getAttributeProvision()).thenReturn(5);
        when(mockCard.getAttributeFaction()).thenReturn("Monsters");
        when(mockCard.getAttributeRarity()).thenReturn("Epic");

        // Act
        List<String> urls = cardLoader.collectURLs("2");

        // Assert
        assertNotNull(urls);
        assertFalse(urls.isEmpty());
        assertTrue(urls.stream().anyMatch(url -> url.contains("trinket_special.png")));
    }

    @Test
    void testCollectURLs_withOtherType_shouldReturnProperUrls() {
        // Arrange
        when(gwentCardsRepository.getReferenceById(3L)).thenReturn(mockCard);
        when(mockCard.getAttributeType()).thenReturn("Leader");
        when(mockCard.getArtId()).thenReturn(789L);
        when(mockCard.getAttributeColor()).thenReturn("Bronze");
        when(mockCard.getAttributeProvision()).thenReturn(7);
        when(mockCard.getAttributeFaction()).thenReturn("Nilfgaard");
        when(mockCard.getAttributeRarity()).thenReturn("Rare");

        // Act
        List<String> urls = cardLoader.collectURLs("3");

        // Assert
        assertNotNull(urls);
        assertFalse(urls.isEmpty());
        assertTrue(urls.stream().anyMatch(url -> url.contains("ability_crown.png")));
    }
}

