package com.bcnc.inditex.application;

import com.bcnc.inditex.domain.Price;
import com.bcnc.inditex.domain.ports.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PriceService {

    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public List<Price> getApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        return priceRepository.findPriceFor(productId, brandId, applicationDate);
    }
}
