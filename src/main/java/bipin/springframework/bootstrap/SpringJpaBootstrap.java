package bipin.springframework.bootstrap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import bipin.springframework.domain.CardType;
import bipin.springframework.repositories.CardTypeRepository;

import java.math.BigDecimal;
import java.util.List;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
    private CardTypeRepository cardTypeRepository;
    private Logger log = LogManager.getLogger(SpringJpaBootstrap.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadcardTypecards();
    }
//It will load the default cards while loading the application
    private void loadcardTypecards() {
        CardType visaCard = new CardType();
        visaCard.setDescription("Sample cards for ATM CARD TYPE");
        visaCard.setPrice(new BigDecimal("20"));
        visaCard.setImageUrl("https://github.com");
        visaCard.setCardId("123456789");
        cardTypeRepository.save(visaCard);

        log.info("Saved visaCard - id: " + visaCard.getId());

        CardType masterCard = new CardType();
        masterCard.setDescription("Sample card type for ATM CARD TYPE");
        masterCard.setImageUrl("https://github.com/bipinkumar");
        masterCard.setCardId("168639393495335947");
        masterCard.setPrice(new BigDecimal("11.95"));
        cardTypeRepository.save(masterCard);

        log.info("Saved masterCard - id:" + masterCard.getId());
    }


    }



