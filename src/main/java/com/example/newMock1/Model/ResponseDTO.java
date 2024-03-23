package com.example.newMock1.Model;

import lombok.Data;

import java.math.BigDecimal;

@Data

public class ResponseDTO {

    private String rqUID;
    private String clientId;
    private String account;
    private String currency;
    private BigDecimal balance;
    private BigDecimal maxLimit;

    public void setRqUID(String rqUID) {
        this.rqUID = rqUID;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setMaxLimit(BigDecimal maxLimit) {
        this.maxLimit = maxLimit;
    }
}
