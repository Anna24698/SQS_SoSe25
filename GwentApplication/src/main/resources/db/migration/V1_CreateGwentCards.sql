use GwentDB;

drop table  IF EXISTS gwent_cards;

CREATE TABLE IF NOT EXISTS  gwent_cards(
                                           cardId BIGINT,
                                           artId BIGINT,
                                           attributeSet VARCHAR(255),
                                           attributeType VARCHAR(255),
                                           attributeColor VARCHAR(255),
                                           attributePower VARCHAR(255),
                                           attributeReach VARCHAR(255),
                                           attributeRarity VARCHAR(255),
                                           attributeFaction VARCHAR(255),
                                           attributeProvision INT,
                                           cardName VARCHAR(255),
                                           cardCategory VARCHAR(255),
                                           cardAbility VARCHAR(255),
                                           cardKeyword VARCHAR(1000)
);