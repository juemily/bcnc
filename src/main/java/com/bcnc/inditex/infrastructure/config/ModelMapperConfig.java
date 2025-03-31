package com.bcnc.inditex.infrastructure.config;

import com.bcnc.inditex.infrastructure.mapper.PriceMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ModelMapperConfig {

    @Primary @Bean
    public PriceMapper PriceMapper() {
        return  new PriceMapper();

    }



}
