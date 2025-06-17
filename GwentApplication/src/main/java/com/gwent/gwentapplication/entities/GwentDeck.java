package com.gwent.gwentapplication.entities;

import jakarta.persistence.*;

@Entity
//@IdClass(GwentDeckKey.class)
public class GwentDeck {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private GwentUsers userId;


    @ManyToOne
    @JoinColumn(name = "cardId", referencedColumnName = "cardId")
    private GwentCards cardId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GwentUsers getUserId() {
        return userId;
    }

    public void setUserID(GwentUsers userId) {
        this.userId = userId;
    }

    public GwentCards getCardId() {
        return cardId;
    }

    public void setCardId(GwentCards cardId) {
        this.cardId = cardId;
    }
}
