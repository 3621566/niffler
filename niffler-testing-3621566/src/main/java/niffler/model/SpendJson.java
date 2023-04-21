package niffler.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpendJson implements Serializable {

    @JsonProperty("id")
    private UUID id;
    @JsonProperty("spendDate")
    private Date spendDate;
    @JsonProperty("category")
    private String category;
    @JsonProperty("currency")
    private CurrencyValues currency;
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("description")
    private String description;
    @JsonProperty("username")
    private String username;

//    public SpendJson setSpendDate(String spendDate) {
//        this.spendDate = DateUtils.reFormatDate(spendDate);
//        return this;
//    }
}