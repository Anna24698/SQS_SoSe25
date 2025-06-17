package com.gwent.gwentapplication.dtos;

import jakarta.persistence.*;

@Entity
@Table(name = "gwentcards")
public class GwentCards {//Singular machen
    @Id
            @Column(name = "cardId")
    private Long cardId;
    @Column(name = "artId")
    private Long artId;
    @Column(name = "attributeSet")
    private String attributeSet;
    @Column(name = "attributeType")
    private String attributeType;
    @Column(name = "attributeColor")
    private String attributeColor;
    @Column(name = "attributePower")
    private String attributePower;
    @Column(name = "attributeReach")
    private String attributeReach;
    @Column(name = "attributeRarity")
    private String attributeRarity;
    @Column(name = "attributeFaction")
    private String attributeFaction;

    @Column(name = "attributeProvision")
    private int attributeProvision;
    @Column(name = "cardName")
    private String cardName;
    @Column(name = "cardCategory")
    private String cardCategory;
    @Column(name = "cardAbility")
    private String cardAbility;
    @Column(name = "cardKeyword", length = 1000)
    //@Lob
    //@Type(type = "org.hibernate.type.TextType")
    private String cardKeyword;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getArtId() {
        return artId;
    }

    public void setArtId(Long artId) {
        this.artId = artId;
    }

    public String getAttributeSet() {
        return attributeSet;
    }

    public void setAttributeSet(String attributeSet) {
        this.attributeSet = attributeSet;
    }

    public String getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(String attributeType) {
        this.attributeType = attributeType;
    }

    public String getAttributeColor() {
        return attributeColor;
    }

    public void setAttributeColor(String attributeColor) {
        this.attributeColor = attributeColor;
    }

    public String getAttributePower() {
        return attributePower;
    }

    public void setAttributePower(String attributePower) {
        this.attributePower = attributePower;
    }

    public String getAttributeReach() {
        return attributeReach;
    }

    public void setAttributeReach(String attributeReach) {
        this.attributeReach = attributeReach;
    }

    public String getAttributeRarity() {
        return attributeRarity;
    }

    public void setAttributeRarity(String attributeRarity) {
        this.attributeRarity = attributeRarity;
    }

    public String getAttributeFaction() {
        return attributeFaction;
    }

    public void setAttributeFaction(String attributeFaction) {
        this.attributeFaction = attributeFaction;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardCategory() {
        return cardCategory;
    }

    public void setCardCategory(String cardCategory) {
        this.cardCategory = cardCategory;
    }

    public String getCardAbility() {
        return cardAbility;
    }

    public void setCardAbility(String cardAbility) {
        this.cardAbility = cardAbility;
    }

    public String getCardKeyword() {
        return cardKeyword;
    }

    public void setCardKeyword(String cardKeyword) {
        this.cardKeyword = cardKeyword;
    }

    public int getAttributeProvision() {
        return attributeProvision;
    }

    public void setAttributeProvision(int attributeProvision) {
        this.attributeProvision = attributeProvision;
    }
}
