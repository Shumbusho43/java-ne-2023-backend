package com.ne.java.services;

import com.ne.java.models.Purchase;
import com.ne.java.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    @Autowired
    private PurchaseRepository purchaseRepository;

    public List<Map<String, Object>> generatePurchaseReport() {
        List<Map<String, Object>> report = new ArrayList<>();
        List<Purchase> purchases = purchaseRepository.findAll();

        for (Purchase purchased : purchases) {
            Map<String, Object> row = new HashMap<>();
            row.put("id", purchased.getId());
//            row.put("customerName", purchased.getCustomer().getFirstName());
            row.put("date", purchased.getDate());
            row.put("productId", purchased.getProduct().getId());
            row.put("productName", purchased.getProduct().getName());
            row.put("quantity", purchased.getQuantity());
            row.put("totalPrice", purchased.getProduct().getPrice() * purchased.getQuantity());
            row.put("unitPrice", purchased.getProduct().getPrice());

            report.add(row);
        }

        return report;
    }

}

