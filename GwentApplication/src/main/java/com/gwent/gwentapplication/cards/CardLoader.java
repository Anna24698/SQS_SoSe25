package com.gwent.gwentapplication.cards;


import com.google.gson.*;
import com.gwent.gwentapplication.entities.GwentCards;
import com.gwent.gwentapplication.repository.GwentCardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.net.http.*;
import java.util.ArrayList;
import java.util.List;

import static com.gwent.gwentapplication.constants.CardConstants.*;
import static java.lang.Long.parseLong;

@Component
public class CardLoader {

    @Autowired
    private GwentCardsRepository gwentCardsRepository;

//Lade die Kartendaten in DB
    @PostConstruct //@TODO vor produktivsetzung wieder einkommentieren
    public void loadCardData() throws Exception {


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .build();


        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString()); // Antwort holen


        String responseString = response.body(); // JSON in JsonObjekt verwandeln
        responseString = "[" + responseString + "]";
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(responseString, JsonArray.class);
        JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
        int numberOfCards = Integer.valueOf( jsonObject.get("request").getAsJsonObject().get("message").getAsString().split(" ")[0] );


        jsonObject = jsonObject.get("response").getAsJsonObject(); // Response des Objekts herausholen
        JsonObject card;// = new JsonObject();
        JsonObject subcard;// = new JsonObject();
        GwentCards gwentCard = new GwentCards();

        for (int i = 0; i < numberOfCards; i++){ //Karten in DB abspeichern
            card = jsonObject.get(String.valueOf(i)).getAsJsonObject();
            subcard = card.get("id").getAsJsonObject();
            gwentCard.setCardId(subcard.get("card").getAsLong());
            gwentCard.setArtId(subcard.get("art").getAsLong());
            subcard = card.get("attributes").getAsJsonObject();
            gwentCard.setAttributeSet(subcard.get("set").getAsString());
            gwentCard.setAttributeType(subcard.get("type").getAsString());
            gwentCard.setAttributeColor(subcard.get("color").getAsString());
            gwentCard.setAttributePower(subcard.get("power").getAsString());
            gwentCard.setAttributeReach(subcard.get("reach").getAsString());
            gwentCard.setAttributeRarity(subcard.get("rarity").getAsString());
            gwentCard.setAttributeFaction(subcard.get("faction").getAsString());
            gwentCard.setAttributeProvision(subcard.get("provision").getAsInt());
            gwentCard.setCardName(card.get("name").getAsString());
            gwentCard.setCardCategory(card.get("category").getAsString());
            gwentCard.setCardAbility(card.get("ability").getAsString());
            gwentCard.setCardKeyword(card.get("keyword_html").getAsString());
            gwentCardsRepository.save(gwentCard);
        }
    }
    //Lade ein PNG und kombininere die PNG-Layers
    public BufferedImage mergeImages(List<String> pictureUrl) throws IOException {
        //Frontend kriegt Karte aus Modell,



        // Erstes Bild laden, um Breite und Höhe festzulegen
        BufferedImage baseImage = ImageIO.read(new URL(pictureUrl.get(0)));
        int width = baseImage.getWidth();
        int height = baseImage.getHeight();

        // Neues leeres Bild mit Alpha-Kanal (Transparenz)
        BufferedImage combined = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = combined.createGraphics();

        // Antialiasing (optional)
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        // Bilder übereinander zeichnen
        for (String url : pictureUrl) {
            BufferedImage layer = ImageIO.read(new URL(url));
            if (layer.getWidth() != width || layer.getHeight() != height) {
                layer = resizeImage(layer, width, height);
            }
            g.drawImage(layer, 0, 0, null);
        }

        g.dispose();
        return combined;
    }
    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g.dispose();
        return resizedImage;
    }

    //erstelle die URLs für mergeImages
    public List<String> collectURLs (String cardId){
        GwentCards card =  gwentCardsRepository.getReferenceById(parseLong(cardId));
        List<String> urls = new ArrayList<>();

        if (card.getAttributeType().equals("Unit")) {
            urls.add(GC_ART_LOW + card.getArtId() + ".jpg");
            urls.add(GC_ART_BORDER + card.getAttributeColor().toLowerCase() + ".png");

            if (card.getAttributeProvision() != 0) {
                urls.add(GC_ART_PROVISION_ICON);
                urls.add(GC_ART_PROVISION_ATTRIBUTE + card.getAttributeFaction().replace(" ", "_").toLowerCase() + ".png");
                urls.add(GC_ART_PROVISION_NUMBER + card.getAttributeProvision() + ".png");
            }
            urls.add(GC_ART_BANNER + card.getAttributeFaction().replace(" ", "_").toLowerCase() + ".png");
            urls.add(GC_ART_POWER + card.getAttributePower() + ".png");
            urls.add(GC_ART_RARITY + card.getAttributeRarity().toLowerCase() + ".png");

        } else if (card.getAttributeType().equals("Special") || card.getAttributeType().equals("Artifact") || card.getAttributeType().equals("Stratagem")) {

            urls.add(GC_ART_LOW + card.getArtId() + ".jpg");
            urls.add(GC_ART_BORDER +  card.getAttributeColor().toLowerCase() + ".png");
            if (card.getAttributeProvision() != 0) {
                urls.add(GC_ART_PROVISION_ICON);
                urls.add(GC_ART_PROVISION_ATTRIBUTE +card.getAttributeFaction().replace(" ", "_").toLowerCase()+".png");
                urls.add(GC_ART_PROVISION_NUMBER + card.getAttributeProvision() + ".png");
            }
            urls.add(GC_ART_BANNER + card.getAttributeFaction().replace(" ", "_").toLowerCase() + ".png");
            urls.add(GC_ART_TRINKET + card.getAttributeType().toLowerCase() +".png");
            urls.add(GC_ART_RARITY + card.getAttributeRarity().toLowerCase() + ".png");
        } else {
            urls.add(GC_ART_LOW + card.getArtId() + ".jpg");
            urls.add(GC_ART_BORDER + card.getAttributeColor().toLowerCase() + ".png");
            urls.add(GC_ART_BANNER_ABILITY + card.getAttributeFaction().replace(" ", "_").toLowerCase() + ".png");
            urls.add(GC_ART_CROWN);
            urls.add(GC_ART_ABILITY_PROVISION);
            urls.add(GC_ART_ABILITY_NUMBER + card.getAttributeProvision() + ".png");
            urls.add(GC_ART_RARITY + card.getAttributeRarity().toLowerCase() + ".png");
        }
        return urls;
    }

}
