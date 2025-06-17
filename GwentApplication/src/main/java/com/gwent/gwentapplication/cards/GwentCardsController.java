package com.gwent.gwentapplication.cards;

import com.gwent.gwentapplication.dtos.GwentCards;
import com.gwent.gwentapplication.dtos.GwentDeck;
import com.gwent.gwentapplication.deck.GwentDeckRepository;
import com.gwent.gwentapplication.dtos.GwentUsers;
import com.gwent.gwentapplication.users.GwentUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.lang.Long.parseLong;

@Controller
public class GwentCardsController {
    //Die Klasse für alles mit Karten
    @Autowired
    private GwentCardsRepository gwentCardsRepository;
    @Autowired
    private GwentDeckRepository gwentDeckRepository;
    @Autowired
    private GwentUsersRepository gwentUsersRepository;

    @Autowired
    private CardLoader cardLoader;
    @GetMapping("/buildDeck")
    public String zeigeDeckbauseite(@RequestParam(name = "leaderId", required = false) Optional<Long> leaderId,
                                    Model model) {

       GwentCards[] cards = gwentCardsRepository.findGwentCardsByAttributeFaction("Neutral").toArray(GwentCards[]::new);
        List<String> links = new ArrayList<>();
        List<String> cardnames = new ArrayList<>();
        for (GwentCards card: cards) {
                links.add("/buildDeck2?id=" + card.getCardId());
                cardnames.add(card.getCardName() + card.getCardId() + card.getAttributeType());
        }
        if (leaderId.isPresent()){
            GwentCards leader = gwentCardsRepository.getReferenceById(leaderId.get());
            cards = gwentCardsRepository.findGwentCardsByAttributeFaction(leader.getAttributeFaction()).toArray(GwentCards[]::new);
            for (GwentCards card: cards) {
                if (!Objects.equals(card.getCardCategory(), "Leader")) {
                    links.add("/buildDeck2?id=" + card.getCardId());
                    cardnames.add(card.getCardName() + card.getCardId() + card.getAttributeType());
                }
            }
            model.addAttribute("currentleadercard", "/buildDeck2?id=" + leader.getCardId() );
            model.addAttribute("currentleadername", leader.getCardName());
        }
        model.addAttribute("links", links.toArray());
        model.addAttribute("cardnames", cardnames.toArray());

        GwentCards[] leaders = gwentCardsRepository.findGwentCardsByCardCategory("Leader").toArray(GwentCards[]::new);
        List<String> leaderlinks = new ArrayList<>();
        List<String> leadernames = new ArrayList<>();
        for (GwentCards leader: leaders) {
            leaderlinks.add("/buildDeck2?id=" + leader.getCardId());
            leadernames.add(leader.getCardName() + leader.getCardId() + leader.getAttributeType());
        }
        model.addAttribute("leaders", leaderlinks.toArray());
        model.addAttribute("leadernames", leadernames.toArray());

        List<String> decklinks = new ArrayList<>();
        List<String> decknames = new ArrayList<>();

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Object principal = authentication.getPrincipal();
        if(!Objects.equals(principal.toString(), "anonymousUser")){
            UserDetails userDetails = (UserDetails) principal;
            String username = userDetails.getUsername();
            GwentUsers user = gwentUsersRepository.findByUsername(username).get();
            if (user != null){
                GwentDeck[] decklines = gwentDeckRepository.findGwentDecksByUserId(user).toArray(GwentDeck[]::new);
                List<Long> cardIds = new ArrayList<>();
                for (GwentDeck deckline : decklines){
                        cardIds.add(deckline.getCardId().getCardId());
                }
                GwentCards[] deckcards = gwentCardsRepository.findGwentCardsByCardIdIn(cardIds).toArray(GwentCards[]::new);

                for (GwentCards card: deckcards){
                    if (card.getCardCategory().equals("Leader") &&  leaderId.isEmpty()){
                        return "redirect:/buildDeck?leaderId="+card.getCardId(); //TODO besser Positionieren
                    } else if (!card.getCardCategory().equals("Leader")){
                        decklinks.add("/buildDeck2?id=" + card.getCardId());
                        decknames.add(card.getCardName());
                    }
                }
            }
        }
        model.addAttribute("deck", decklinks);
        model.addAttribute("decknames", decknames);

        return "buildDeck";
    }

    @GetMapping("/buildDeck2")
    public ResponseEntity<byte[]> getCompositeImage(@RequestParam String id) throws IOException {
//Nie Byte[] ans Frontend
        //Bulkrequests
        //Zusammenfügen im Frontend -> ins ADR


        List<String> urls = cardLoader.collectURLs(id);

        if (!urls.isEmpty()) {
            BufferedImage image = cardLoader.mergeImages(urls);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(baos.toByteArray());
        }
        return null;
    }

    @PostMapping("/submit-deck")
    public ResponseEntity<String> receiveDeck(@RequestBody List<String> deck,
                                              @RequestParam(name = "leaderId", required = false) Optional<Long> leaderId ) {
      //Deck ist eine Liste aus Links
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
       String username = userDetails.getUsername();


        GwentUsers user = gwentUsersRepository.findByUsername(username).get();
        GwentDeck deckline = new GwentDeck();
        deckline.setUserID(user);
        gwentDeckRepository.deleteAll( gwentDeckRepository.findGwentDecksByUserId(user));
        gwentDeckRepository.flush();
        Long id = gwentDeckRepository.findMaxId();
        for (String card: deck ){
            card = card.substring(15);
           if (gwentCardsRepository.findById(Long.parseLong(card)).isPresent()) {
               id = id + 1;
               deckline.setId(id);
               deckline.setCardId(gwentCardsRepository.findById(Long.parseLong(card)).get());
               gwentDeckRepository.save(deckline);
           }
        }
        //Die LeaderCard
        if (leaderId.isPresent()) {
            id = id + 1;
            deckline.setId(id);
            deckline.setCardId(gwentCardsRepository.getReferenceById(leaderId.get()));
            gwentDeckRepository.save(deckline);
        }
        return ResponseEntity.ok("Deck empfangen!");
    }
}
