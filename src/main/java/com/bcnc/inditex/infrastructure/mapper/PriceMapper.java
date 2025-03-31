package com.bcnc.inditex.infrastructure.mapper;


import com.bcnc.inditex.domain.Price;
import com.bcnc.inditex.infrastructure.persistence.PriceEntity;
import com.bcnc.inditex.infrastructure.web.dto.PriceDto;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper {
    
    public Price mapToDomain(PriceEntity entity) {

        Price response = new Price();

        response.setProductId(entity.getProductId());
        response.setBrandId(entity.getBrandId());
        response.setPriceList(entity.getPriceList());
        response.setStartDate(entity.getStartDate());
        response.setEndDate(entity.getEndDate());
        response.setPrice(entity.getPrice());
        response.setCurrency(entity.getCurrency());

        return response;

    }


    public PriceDto mapToDataObject(Price entity) {

        PriceDto response = new PriceDto();

        response.setProductId(entity.getProductId());
        response.setBrandId(entity.getBrandId());
        response.setPriceList(entity.getPriceList());
        response.setStartDate(entity.getStartDate());
        response.setEndDate(entity.getEndDate());
        response.setPrice(entity.getPrice());
        response.setCurrency(entity.getCurrency());

        return response;

    }


}
