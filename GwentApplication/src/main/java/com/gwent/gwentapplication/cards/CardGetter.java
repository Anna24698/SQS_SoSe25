package com.gwent.gwentapplication.cards;

/*
@Component
public class CardGetter {
    @Autowired
    private GwentCardsRepository gwentCardsRepository;
    @Autowired
    private GwentDeckRepository gwentDeckRepository;
    @Autowired
    private GwentUsersRepository gwentUsersRepository;

    @Autowired
    private CardLoader cardLoader;


    public ArrayList<> getCardsOfUser() throws {
        //if (userId.isPresent() ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        //String user = principal.toString();
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
    }
}*/
