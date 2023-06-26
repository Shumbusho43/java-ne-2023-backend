package com.ne.java.controllers;

import com.ne.java.services.CartService;
import com.ne.java.services.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/purchases")
    public ResponseEntity<List<Map<String, Object>>> generatePurchaseReport() {
        List<Map<String, Object>> report = reportService.generatePurchaseReport();
        return ResponseEntity.ok(report);
    }
}


