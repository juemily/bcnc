package com.bcnc.inditex.infrastructure.web;

import com.bcnc.inditex.application.PriceService;
import com.bcnc.inditex.domain.Price;
import com.bcnc.inditex.infrastructure.mapper.PriceMapper;
import com.bcnc.inditex.infrastructure.web.dto.PriceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final PriceService priceService;
    private final PriceMapper mapper;

    public PriceController(PriceService priceService, PriceMapper mapper) {
        this.priceService = priceService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<PriceDto>> getApplicablePrices(
            @RequestParam("applicationDate") String applicationDate,
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Long brandId) {

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(applicationDate, formatter);


            List<Price> prices = priceService.getApplicablePrice(productId, brandId, localDateTime);


            if (!prices.isEmpty()) {
                List<PriceDto> priceDtos = prices.stream()
                        .map(mapper::mapToDataObject).toList();

                return ResponseEntity.ok(priceDtos);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
