package com.sapa.app.service.deposit.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepositRequest {
    private BigDecimal amountInMinor;
    private String paymentRef; //Payment Gateway
}