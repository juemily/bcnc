package com.bcnc.inditex.infrastructure.adapter;

import com.bcnc.inditex.domain.Price;
import com.bcnc.inditex.domain.ports.PriceRepository;

import com.bcnc.inditex.infrastructure.mapper.PriceMapper;
import com.bcnc.inditex.infrastructure.persistence.PriceEntity;
import com.bcnc.inditex.infrastructure.persistence.PriceJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PriceRepositoryAdapter implements PriceRepository {

    private final PriceJpaRepository jpaRepository;

    private final PriceMapper mapper;

    public PriceRepositoryAdapter(PriceJpaRepository jpaRepository, PriceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Price> findPriceFor(Long productId, Long brandId, LocalDateTime applicationDate) {
        List<PriceEntity> pricesFound = jpaRepository.findApplicablePrices(productId, brandId, applicationDate);
        return pricesFound.stream()
                .map(mapper::mapToDomain)
                .collect(Collectors.toList());
    }
    /*public List<Price> findPriceFor(Long productId, Long brandId, LocalDateTime applicationDate) {

        List<PriceEntity> pricesFound = jpaRepository.findApplicablePrices(productId, brandId, applicationDate);
        return pricesFound.stream()
                .map(priceEntity -> mapper.map(priceEntity, Price.class)).collect(Collectors.toList());
    }*/


}

