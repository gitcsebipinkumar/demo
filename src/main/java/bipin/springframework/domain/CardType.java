package bipin.springframework.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class CardType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated card ID")
    private Integer id;
    @Version
    @ApiModelProperty(notes = "The auto-generated version of the card")
    private Integer version;
    @ApiModelProperty(notes = "The application-specific card ID")
    private String cardId;
    @ApiModelProperty(notes = "The card description")
    private String description;
    @ApiModelProperty(notes = "The image URL of the card")
    private String imageUrl;
    @ApiModelProperty(notes = "The price of the card", required = true)
    private BigDecimal price;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
