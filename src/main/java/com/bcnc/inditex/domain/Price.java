package com.bcnc.inditex.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Price {

    private Long brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int priceList;
    private Long productId;
    private int priority;
    private BigDecimal price;
    private String currency;

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
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

    public int getPriceList() {
        return priceList;
    }

    public void setPriceList(int priceList) {
        this.priceList = priceList;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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
        Price price1 = (Price) o;
        return priceList == price1.priceList && priority == price1.priority && Objects.equals(brandId, price1.brandId) && Objects.equals(startDate, price1.startDate) && Objects.equals(endDate, price1.endDate) && Objects.equals(productId, price1.productId) && Objects.equals(price, price1.price) && Objects.equals(currency, price1.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandId, startDate, endDate, priceList, productId, priority, price, currency);
    }

    @Override
    public String toString() {
        return "Price{" +
                "brandId=" + brandId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", priceList=" + priceList +
                ", productId=" + productId +
                ", priority=" + priority +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }
}
