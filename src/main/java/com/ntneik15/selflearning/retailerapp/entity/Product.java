package com.ntneik15.selflearning.retailerapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Table(name = "products", schema = "public", indexes = {
        @Index(name = "productline", columnList = "productline")
})
public class Product {
    @Id
    @Size(max = 15)
    @Column(name = "productcode", nullable = false, length = 15)
    private String productcode;

    @Size(max = 70)
    @NotNull
    @Column(name = "productname", nullable = false, length = 70)
    private String productname;


    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "productline", nullable = false)
    private Productline productline;

    @Size(max = 10)
    @NotNull
    @Column(name = "productscale", nullable = false, length = 10)
    private String productscale;

    @Size(max = 50)
    @NotNull
    @Column(name = "productvendor", nullable = false, length = 50)
    private String productvendor;

    @NotNull
    @Column(name = "productdescription", nullable = false, length = Integer.MAX_VALUE)
    private String productdescription;

    @NotNull
    @Column(name = "quantityinstock", nullable = false)
    private Short quantityinstock;

    @NotNull
    @Column(name = "buyprice", nullable = false, precision = 10, scale = 2)
    private BigDecimal buyprice;

    @NotNull
    @Column(name = "msrp", nullable = false, precision = 10, scale = 2)
    private BigDecimal msrp;

    @OneToMany(mappedBy = "productcode")
    private Set<Orderdetail> orderdetails = new LinkedHashSet<>();

    public String getId() {
        return productcode;
    }

    public void setId(String id) {
        this.productcode = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Productline getProductline() {
        return productline;
    }

    public void setProductline(Productline productline) {
        this.productline = productline;
    }

    public String getProductscale() {
        return productscale;
    }

    public void setProductscale(String productscale) {
        this.productscale = productscale;
    }

    public String getProductvendor() {
        return productvendor;
    }

    public void setProductvendor(String productvendor) {
        this.productvendor = productvendor;
    }

    public String getProductdescription() {
        return productdescription;
    }

    public void setProductdescription(String productdescription) {
        this.productdescription = productdescription;
    }

    public Short getQuantityinstock() {
        return quantityinstock;
    }

    public void setQuantityinstock(Short quantityinstock) {
        this.quantityinstock = quantityinstock;
    }

    public BigDecimal getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(BigDecimal buyprice) {
        this.buyprice = buyprice;
    }

    public BigDecimal getMsrp() {
        return msrp;
    }

    public void setMsrp(BigDecimal msrp) {
        this.msrp = msrp;
    }

    public Set<Orderdetail> getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(Set<Orderdetail> orderdetails) {
        this.orderdetails = orderdetails;
    }

}