package com.example.premidterm2;

import org.springframework.stereotype.Service;

@Service
public class CalculatorPriceService {
    public double getPrice(double cost, double profit){
        return cost+profit;
    }
}
