package com.nttdata.bootcamp.mscredit.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document (collection = "credits")
@Data
public class Credit {

    @Id
    private Integer id;
    private String cardNumber;
    private Float creditLimit;
    private String expiryDate;

    private Float availableBalance;

    public Float calculateDebt(){
        return creditLimit-availableBalance;
    }

}
