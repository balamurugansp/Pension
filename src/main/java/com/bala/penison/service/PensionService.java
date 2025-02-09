package com.bala.penison.service;


import org.springframework.stereotype.Service;

import com.bala.penison.error.PensionAmountNullException;
import com.bala.penison.error.PensionerNotFoundException;

import java.util.HashMap;
import java.util.Map;

@Service
public class PensionService {

    private Map<String, Double> pensionerData = new HashMap<>();

    public PensionService() {
        pensionerData.put("Alice", 5000.0);
        pensionerData.put("Bob", 7000.0);
        pensionerData.put("Charlie", null); // Example of null value
    }

    public Double getPensionAmount(String pensionerName) {
        if (!pensionerData.containsKey(pensionerName)) {
            throw new PensionerNotFoundException("Pensioner not found: " + pensionerName);
        }
        Double pensionAmount = pensionerData.get(pensionerName);
        if (pensionAmount == null) {
            throw new PensionAmountNullException("Pension amount is null for: " + pensionerName);
        }
        return pensionAmount;
    }
}



