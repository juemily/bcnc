package com.bcnc.inditex.infrastructure.web;

import com.bcnc.inditex.application.PriceService;
import com.bcnc.inditex.domain.Price;
import com.bcnc.inditex.infrastructure.mapper.PriceMapper;
import com.bcnc.inditex.infrastructure.web.dto.PriceDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PriceService priceService;

    @Mock
    private PriceMapper priceMapper;

    @InjectMocks
    private PriceController priceController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(priceController).build();
    }


    @Test
    void shouldReturnApplicablePricesWhenFound() throws Exception {
        LocalDateTime dateTime = LocalDateTime.of(2023, 6, 14, 10, 0, 0);
        Price price = new Price();
        PriceDto priceDto = new PriceDto();

        when(priceService.getApplicablePrice(1L, 1L, dateTime)).thenReturn(List.of(price));
        when(priceMapper.mapToDataObject(price)).thenReturn(priceDto);

        mockMvc.perform(get("/api/prices")
                        .param("applicationDate", "2023-06-14 10:00:00")
                        .param("productId", "1")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    void shouldReturnNotFoundWhenNoApplicablePrices() throws Exception {
        LocalDateTime dateTime = LocalDateTime.of(2023, 6, 14, 10, 0, 0);

        when(priceService.getApplicablePrice(1L, 1L, dateTime)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/prices")
                        .param("applicationDate", "2023-06-14 10:00:00")
                        .param("productId", "1")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnBadRequestForInvalidDateFormat() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("applicationDate", "invalid-date")
                        .param("productId", "1")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}