package bipin.springframework.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import bipin.springframework.domain.CardType;
import bipin.springframework.repositories.CardTypeRepository;
import bipin.springframework.services.CardTypeServiceImpl;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class CardServiceImplSpyTest {
    @Spy
    private CardTypeServiceImpl cardServiceSpy;
    @Mock
    private CardTypeRepository cardTypeRepository;
    @Mock
    private CardType card;

    @Test(expected=NullPointerException.class)
    public void shouldThrowNullPointerException_whenGetCardByIdIsCalledWithoutContext() throws Exception {
        //Act
        CardType retrievedCard = cardServiceSpy.getCardTypeById(5);
        //Assert
        assertThat(retrievedCard, is(equalTo(card)));
    }

    public void shouldThrowNullPointerException_whenSaveCardIsCalledWithoutContext() throws Exception {
        //Arrange
        Mockito.doReturn(card).when(cardTypeRepository).save(card);
        //Act
        CardType savedCard = cardServiceSpy.saveCardType(card);
        //Assert
        assertThat(savedCard, is(equalTo(card)));
    }

    @Test
    public void shouldVerifyThatGetCardByIdIsCalled() throws Exception {
        //Arrange
        Mockito.doReturn(card).when(cardServiceSpy).getCardTypeById(5);
        //Act
        CardType retrievedCard = cardServiceSpy.getCardTypeById(5);
        //Assert
        Mockito.verify(cardServiceSpy).getCardTypeById(5);
    }
    @Test
    public void shouldVerifyThatSaveCardIsNotCalled() throws Exception {
        //Arrange
        Mockito.doReturn(card).when(cardServiceSpy).getCardTypeById(5);
        //Act
        CardType retrievedCard = cardServiceSpy.getCardTypeById(5);
        //Assert
        Mockito.verify(cardServiceSpy,never()).saveCardType(card);
    }
}