package com.nttdata.bootcamp.mscredit.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document (collection = "credits")
@Data
@AllArgsConstructor
@Generated
public class Credit {

    @Id
    private Integer id;
    private String cardNumber;
    private Float creditLimit;
    private String expiryDate;

    private Float availableBalance;

    private String creditType;

    public Float calculateDebt(){
        return creditLimit-availableBalance;
    }

}
