package bipin.springframework.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bipin.springframework.domain.CardType;
import bipin.springframework.repositories.CardTypeRepository;

@Service
public class CardTypeServiceImpl implements CardTypeService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private CardTypeRepository cardRepository;

    @Autowired
    public void setCardRepository(CardTypeRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Iterable<CardType> listAllCards() {
        logger.debug("listAllCards called");
        return cardRepository.findAll();
    }

    @Override
    public CardType getCardTypeById(Integer id) {
        logger.debug("getCardTypeById called");
        return cardRepository.findById(id).orElse(null);
    }

    @Override
    public CardType saveCardType(CardType card) {
        logger.debug("savecard called");
        return cardRepository.save(card);
    }

    @Override
    public void deleteCardType(Integer id) {
        logger.debug("deletecard called");
        cardRepository.deleteById(id);
    }
}
