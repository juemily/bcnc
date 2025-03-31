package com.bcnc.inditex.domain.ports;

import com.bcnc.inditex.domain.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {
    List<Price> findPriceFor(Long productId, Long brandId, LocalDateTime applicationDate);
}
