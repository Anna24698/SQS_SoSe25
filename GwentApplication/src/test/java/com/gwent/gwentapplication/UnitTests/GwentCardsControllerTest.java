package com.gwent.gwentapplication.UnitTests;


import com.gwent.gwentapplication.loader.CardLoader;
import com.gwent.gwentapplication.entities.GwentCards;
import com.gwent.gwentapplication.controller.GwentCardsController;
import com.gwent.gwentapplication.entities.GwentDeck;
import com.gwent.gwentapplication.repository.GwentCardsRepository;
import com.gwent.gwentapplication.repository.GwentDeckRepository;
import com.gwent.gwentapplication.entities.GwentUsers;
import com.gwent.gwentapplication.repository.GwentUsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GwentCardsControllerTest {
    @Mock
    private GwentCardsRepository gwentCardsRepository;

    @Mock
    private GwentDeckRepository gwentDeckRepository;


    @Mock
    private GwentUsersRepository gwentUsersRepository;

    @Mock
    private CardLoader cardLoader;

    @Mock
    private Model model;

    @InjectMocks
    private GwentCardsController controller;

    @Mock
    private Authentication authentication;

    @Mock
    private UserDetails userDetails;

    @Mock
    private SecurityContext securityContext;

    @BeforeEach
    void setupSecurity() {
        SecurityContextHolder.setContext(securityContext);
        lenient().when(securityContext.getAuthentication()).thenReturn(authentication);
    }

    @Test
    void testZeigeDeckbauseite_withoutLeaderId_andAnonymousUser() {
        when(authentication.getPrincipal()).thenReturn("anonymousUser");
        List<GwentCards> neutralCards = Collections.singletonList(createCard(1L, "NeutralCard", "Neutral", "Type1", "Normal"));
        when(gwentCardsRepository.findGwentCardsByAttributeFaction("Neutral"))
                .thenReturn(neutralCards);

        List<GwentCards> leaders = Collections.singletonList(createCard(2L, "LeaderCard", "Neutral", "TypeLeader", "Leader"));
        when(gwentCardsRepository.findGwentCardsByCardCategory("Leader"))
                .thenReturn(leaders);

        String viewName = controller.zeigeDeckbauseite(Optional.empty(), model);

        assertThat(viewName).isEqualTo("buildDeck");
        verify(model).addAttribute(eq("links"), any());
        verify(model).addAttribute(eq("cardnames"), any());
        verify(model).addAttribute(eq("leaders"), any());
        verify(model).addAttribute(eq("leadernames"), any());
        verify(model).addAttribute(eq("deck"), any());
        verify(model).addAttribute(eq("decknames"), any());
    }

    @Test
    void testZeigeDeckbauseite_withLeaderId_andAnonymousUser() {
        // Setup leader
        Long leaderIdValue = 10L;
        GwentCards leader = createCard(leaderIdValue, "Leader", "Scoiatael", "TypeLeader", "Leader");

        // Mock SecurityContext
        when(authentication.getPrincipal()).thenReturn("anonymousUser");

        // Mock DB returns
        when(gwentCardsRepository.getReferenceById(leaderIdValue)).thenReturn(leader);
        when(gwentCardsRepository.findGwentCardsByAttributeFaction("Scoiatael"))
                .thenReturn(Collections.singletonList(createCard(20L, "Card1", "Scoiatael", "Type1", "Normal")));
        when(gwentCardsRepository.findGwentCardsByAttributeFaction("Neutral"))
                .thenReturn(Collections.singletonList(createCard(99L, "NeutralCard", "Neutral", "TypeNeutral", "Normal")));
        when(gwentCardsRepository.findGwentCardsByCardCategory("Leader"))
                .thenReturn(Collections.singletonList(leader));

        // Call controller method
        String viewName = controller.zeigeDeckbauseite(Optional.of(leaderIdValue), model);

        // Assertions
        assertThat(viewName).isEqualTo("buildDeck");

        // Verify model attributes for leader context
        verify(model).addAttribute("currentleadercard", "/buildDeck2?id=10");
        verify(model).addAttribute("currentleadername", "Leader");

        // Verify common attributes
        verify(model).addAttribute(eq("links"), any());
        verify(model).addAttribute(eq("cardnames"), any());
        verify(model).addAttribute(eq("leaders"), any());
        verify(model).addAttribute(eq("leadernames"), any());
        verify(model).addAttribute(eq("deck"), any());
        verify(model).addAttribute(eq("decknames"), any());
    }

    @Test
    void testZeigeDeckbauseite_withoutLeaderId_andAuthenticatedUser() {
        // Setup
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn("user");

        GwentUsers user = new GwentUsers();
        when(gwentUsersRepository.findByUsername("user")).thenReturn(Optional.of(user));

        GwentCards neutralCard = createCard(1L, "NeutralCard", "Neutral", "Type1", "Normal");
        when(gwentCardsRepository.findGwentCardsByAttributeFaction("Neutral"))
                .thenReturn(Collections.singletonList(neutralCard));

        GwentCards leaderCard = createCard(10L, "LeaderCard", "Northern", "TypeLeader", "Leader");
        when(gwentCardsRepository.findGwentCardsByCardCategory("Leader"))
                .thenReturn(Collections.singletonList(leaderCard));

        GwentCards deckCard = createCard(2L, "DeckCard", "Neutral", "Type2", "Normal");
        GwentDeck deckEntry = new GwentDeck();
        deckEntry.setCardId(deckCard);
        deckEntry.setUserID(user);
        when(gwentDeckRepository.findGwentDecksByUserId(user))
                .thenReturn(Collections.singletonList(deckEntry));

        when(gwentCardsRepository.findGwentCardsByCardIdIn(List.of(2L)))
                .thenReturn(Collections.singletonList(deckCard));

        // Call the method under test
        String viewName = controller.zeigeDeckbauseite(Optional.empty(), model);

        // Verify
        assertThat(viewName).isEqualTo("buildDeck");

        verify(model).addAttribute(eq("links"), any());
        verify(model).addAttribute(eq("cardnames"), any());
        verify(model).addAttribute(eq("leaders"), any());
        verify(model).addAttribute(eq("leadernames"), any());
        verify(model).addAttribute(eq("deck"), any());
        verify(model).addAttribute(eq("decknames"), any());
    }
/*
    @Test
    void testZeigeDeckbauseite_withLeaderId_andAuthenticatedUser() {
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn("user");
        GwentUsers user = new GwentUsers();
        when(gwentUsersRepository.findByUsername("user")).thenReturn(Optional.of(user));

        GwentCards leader = createCard(10L, "Leader", "Northern", "TypeLeader", "Leader");
        when(gwentCardsRepository.getReferenceById(10L)).thenReturn(leader);
        when(gwentCardsRepository.findGwentCardsByAttributeFaction("Northern"))
                .thenReturn(Collections.singletonList(leader));

        when(gwentDeckRepository.findGwentDecksByUserId(user))
                .thenReturn(Collections.emptyList());

        when(gwentCardsRepository.findGwentCardsByCardCategory("Leader"))
                .thenReturn(Collections.singletonList(leader));

        String viewName = controller.zeigeDeckbauseite(Optional.of(10L), model);

        assertThat(viewName).isEqualTo("buildDeck");
        verify(model).addAttribute("currentleadercard", "/buildDeck2?id=10");
        verify(model).addAttribute("currentleadername", "Leader");
        verify(model).addAttribute(eq("links"), any());
        verify(model).addAttribute(eq("cardnames"), any());
        verify(model).addAttribute(eq("leaders"), any());
        verify(model).addAttribute(eq("leadernames"), any());
        verify(model).addAttribute(eq("deck"), any());
        verify(model).addAttribute(eq("decknames"), any());
    }
*/
    @Test
    void testGetCompositeImage_success() throws IOException {
        List<String> urls = Arrays.asList("url1", "url2");
        when(cardLoader.collectURLs("5")).thenReturn(urls);

        BufferedImage mockImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        when(cardLoader.mergeImages(urls)).thenReturn(mockImage);

        var response = controller.getCompositeImage("5");

        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.IMAGE_PNG);
        assertThat(response.getBody()).isNotEmpty();
    }

    @Test
    void testGetCompositeImage_noUrls() throws IOException {
        when(cardLoader.collectURLs("5")).thenReturn(Collections.emptyList());

        var response = controller.getCompositeImage("5");

        assertThat(response).isNull();
    }

    @Test
    void testReceiveDeck_success() {
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn("user");

        GwentUsers user = new GwentUsers();
        when(gwentUsersRepository.findByUsername("user")).thenReturn(Optional.of(user));

        GwentCards card = createCard(1L, "Card", "Neutral", "Type1", "Normal");
        when(gwentCardsRepository.findById(1L)).thenReturn(Optional.of(card));
        when(gwentDeckRepository.findMaxId()).thenReturn(100L);

        List<String> deckLinks = Arrays.asList("/buildDeck2?id=1");
        var response = controller.receiveDeck(deckLinks, Optional.of(2L));

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo("Deck empfangen!");
        verify(gwentDeckRepository).deleteAll(any());
        verify(gwentDeckRepository).flush();
        verify(gwentDeckRepository, atLeastOnce()).save(any());
    }

    private GwentCards createCard(Long id, String name, String faction, String type, String category) {
        GwentCards card = new GwentCards();
        card.setCardId(id);
        card.setCardName(name);
        card.setAttributeFaction(faction);
        card.setAttributeType(type);
        card.setCardCategory(category);
        return card;
    }
}
