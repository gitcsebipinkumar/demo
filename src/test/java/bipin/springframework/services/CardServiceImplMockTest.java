package bipin.springframework.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import bipin.springframework.domain.CardType;
import bipin.springframework.repositories.CardTypeRepository;
import bipin.springframework.services.CardTypeServiceImpl;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;



public class CardServiceImplMockTest {

    private CardTypeServiceImpl cardServiceImpl;
    @Mock
    private CardTypeRepository cardRepository;
    @Mock
    private CardType card;
    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        cardServiceImpl=new CardTypeServiceImpl();
        cardServiceImpl.setCardRepository(cardRepository);
    }
    @Test
    public void shouldReturnCard_whenGetCardByIdIsCalled() throws Exception {
        // Arrange
        when(cardRepository.findById(5)).thenReturn(Optional.of(card));
        // Act
        CardType retrievedCard = cardServiceImpl.getCardTypeById(5);
        // Assert
        assertThat(retrievedCard, is(equalTo(card)));

    }
    @Test
    public void shouldReturnCard_whenSaveCardIsCalled() throws Exception {
        // Arrange
        when(cardRepository.save(card)).thenReturn(card);
        // Act
        CardType savedCard = cardServiceImpl.saveCardType(card);
        // Assert
        assertThat(savedCard, is(equalTo(card)));
    }
    @Test
    public void shouldCallDeleteMethodOfCardRepository_whenDeleteCardIsCalled() throws Exception {
        // Arrange
        doNothing().when(cardRepository).deleteById(5);
        CardTypeRepository my = Mockito.mock(CardTypeRepository.class);
        // Act
        cardServiceImpl.deleteCardType(5);
        // Assert
        verify(cardRepository, times(1)).deleteById(5);
    }
}