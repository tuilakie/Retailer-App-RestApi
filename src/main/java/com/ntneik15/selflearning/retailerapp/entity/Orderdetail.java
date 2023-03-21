package com.ntneik15.selflearning.retailerapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "orderdetails", schema = "public", indexes = {
        @Index(name = "productcode", columnList = "productcode")
})
public class Orderdetail {
    @EmbeddedId
    private OrderdetailId id;

    @MapsId("ordernumber")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ordernumber", nullable = false)
    private Order ordernumber;

    @MapsId("productcode")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "productcode", nullable = false)
    private Product productcode;

    @NotNull
    @Column(name = "quantityordered", nullable = false)
    private Long quantityordered;

    @NotNull
    @Column(name = "priceeach", nullable = false, precision = 10, scale = 2)
    private BigDecimal priceeach;

    @NotNull
    @Column(name = "orderlinenumber", nullable = false)
    private Short orderlinenumber;

    public OrderdetailId getId() {
        return id;
    }

    public void setId(OrderdetailId id) {
        this.id = id;
    }

    public Order getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(Order ordernumber) {
        this.ordernumber = ordernumber;
    }

    public Product getProductcode() {
        return productcode;
    }

    public void setProductcode(Product productcode) {
        this.productcode = productcode;
    }

    public Long getQuantityordered() {
        return quantityordered;
    }

    public void setQuantityordered(Long quantityordered) {
        this.quantityordered = quantityordered;
    }

    public BigDecimal getPriceeach() {
        return priceeach;
    }

    public void setPriceeach(BigDecimal priceeach) {
        this.priceeach = priceeach;
    }

    public Short getOrderlinenumber() {
        return orderlinenumber;
    }

    public void setOrderlinenumber(Short orderlinenumber) {
        this.orderlinenumber = orderlinenumber;
    }

}