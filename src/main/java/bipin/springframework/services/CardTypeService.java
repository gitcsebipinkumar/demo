package bipin.springframework.services;


import bipin.springframework.domain.CardType;

public interface CardTypeService {
    Iterable<CardType> listAllCards();

    CardType getCardTypeById(Integer id);

    CardType saveCardType(CardType card);

    void deleteCardType(Integer id);
}
