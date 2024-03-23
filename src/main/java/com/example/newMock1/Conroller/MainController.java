package com.example.newMock1.Conroller;

import com.example.newMock1.Model.RequestDTO;
import com.example.newMock1.Model.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

@RestController
public class MainController {

    private Logger log = LoggerFactory.getLogger(MainController.class);
    ObjectMapper mapper = new ObjectMapper();

    @PostMapping(
            value = "/info/postBalances",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )

    public Object postBalances(@RequestBody RequestDTO requestDTO){

        try {
            String clienId = requestDTO.getClientId();
            char firstSymbol = clienId.charAt(0);
            BigDecimal maxLimit;
            String currency = "";

            if (firstSymbol == '8') {
                maxLimit = new BigDecimal(2000);
                currency = "US";
            } else if (firstSymbol == '9') {
                maxLimit = new BigDecimal(1000);
                currency = "EU";
            } else {
                maxLimit = new BigDecimal(10000);
                currency = "RUB";
            }

            Random rand = new Random();
            BigDecimal balance = BigDecimal.valueOf(rand.nextDouble() * maxLimit.doubleValue());
            balance = balance.setScale(2, RoundingMode.HALF_UP); // округляем до 2 знаков после запятой

            ResponseDTO responseDTO = new ResponseDTO();

            responseDTO.setRqUID(requestDTO.getRqUID());
            responseDTO.setClientId(clienId);
            responseDTO.setAccount(requestDTO.getAccount());
            responseDTO.setCurrency(currency);
            responseDTO.setBalance(balance);
            responseDTO.setMaxLimit(maxLimit);

            log.error("************* RequestDTO *************" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTO));
            log.error("************* ResponseDTO *************" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDTO));

            return responseDTO;

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }


    }

}
