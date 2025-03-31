package com.bcnc.inditex.infrastructure.web.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PriceDto {

    private Long productId;
    private Long brandId;
    private Integer priceList;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endDate;
    private BigDecimal price;
    private String currency;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Integer getPriceList() {
        return priceList;
    }

    public void setPriceList(Integer priceList) {
        this.priceList = priceList;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceDto priceDto = (PriceDto) o;
        return Objects.equals(productId, priceDto.productId) && Objects.equals(brandId, priceDto.brandId) && Objects.equals(priceList, priceDto.priceList) && Objects.equals(startDate, priceDto.startDate) && Objects.equals(endDate, priceDto.endDate) && Objects.equals(price, priceDto.price) && Objects.equals(currency, priceDto.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, brandId, priceList, startDate, endDate, price, currency);
    }

    @Override
    public String toString() {
        return "PriceDto{" +
                "productId=" + productId +
                ", brandId=" + brandId +
                ", priceList=" + priceList +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }
}
