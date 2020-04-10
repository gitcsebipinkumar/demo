package bipin.springframework.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import bipin.springframework.configuration.RepositoryConfiguration;
import bipin.springframework.domain.CardType;
import bipin.springframework.repositories.CardTypeRepository;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class CardRepositoryTest {
    private CardTypeRepository cardRepository;
    @Autowired
    public void setcardRepository(CardTypeRepository cardRepository) {
        this.cardRepository = cardRepository;
    }
    @Test
    public void testSaveCard(){
        //setup card
        CardType card = new CardType();
        card.setDescription("Spring Framework ");
        card.setPrice(new BigDecimal("18.95"));
        card.setCardId("1234");
        //save card, verify has ID value after save
        assertNull(card.getId()); //null before save
        cardRepository.save(card);
        assertNotNull(card.getId()); //not null after save
        //fetch from DB
        CardType fetchedCardType = cardRepository.findById(card.getId()).orElse(null);
        //should not be null
        assertNotNull(fetchedCardType);
        //should equal
        assertEquals(card.getId(), fetchedCardType.getId());
        assertEquals(card.getDescription(), fetchedCardType.getDescription());
        //update description and save
        fetchedCardType.setDescription("New Description");
        cardRepository.save(fetchedCardType);
        //get from DB, should be updated
        CardType fetchedUpdatedCard = cardRepository.findById(fetchedCardType.getId()).orElse(null);
        assertEquals(fetchedCardType.getDescription(), fetchedUpdatedCard.getDescription());
        //verify count of card in DB
        long cardCount = cardRepository.count();
        assertEquals(cardCount, 1);
        //get all cards, list should only have one
        Iterable<CardType> cards = cardRepository.findAll();
        int count = 0;
        for(CardType p : cards){
            count++;
        }
        assertEquals(count, 1);
    }
}
