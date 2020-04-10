package bipin.springframework.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import bipin.springframework.domain.CardType;
import bipin.springframework.services.CardTypeService;

@RestController
@RequestMapping("/cardtype")
@Api(value="BANKING DOMAIN", description="Operations to perform various card type in BANKING PLATFORM")
public class CardTypeController {

	 @Autowired
	private CardTypeService cardTypeService;


    @ApiOperation(value = "View a list of available cards type",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "/list", method= RequestMethod.GET, produces = "application/json")
    public Iterable<CardType> list(Model model){
        Iterable<CardType> cardList = cardTypeService.listAllCards();
        return cardList;
    }
    @ApiOperation(value = "Search a cardType with an ID",response = CardType.class)
    @RequestMapping(value = "/show/{id}", method= RequestMethod.GET, produces = "application/json")
    public CardType showcard(@PathVariable Integer id, Model model){
       CardType card = cardTypeService.getCardTypeById(id);
        return card;
    }

    @ApiOperation(value = "Add a card")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveCard(@RequestBody CardType cardType){
    	cardTypeService.saveCardType(cardType);
        return new ResponseEntity("card saved successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Update a card")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateCard(@PathVariable Integer id, @RequestBody CardType card){
        CardType storedCard = cardTypeService.getCardTypeById(id);
        storedCard.setDescription(card.getDescription());
        storedCard.setImageUrl(card.getImageUrl());
        storedCard.setPrice(card.getPrice());
        cardTypeService.saveCardType(storedCard);
        return new ResponseEntity("card updated successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a card")
    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity delete(@PathVariable Integer id){
    	cardTypeService.deleteCardType(id);
        return new ResponseEntity("Card deleted successfully", HttpStatus.OK);

    }

}
