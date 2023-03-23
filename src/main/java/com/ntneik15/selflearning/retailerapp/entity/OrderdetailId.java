package com.ntneik15.selflearning.retailerapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class OrderdetailId implements Serializable {
    private static final long serialVersionUID = -7999055908835077096L;
    @NotNull
    @Column(name = "ordernumber", nullable = false)
    private Long ordernumber;

    @Size(max = 15)
    @NotNull
    @Column(name = "productcode", nullable = false, length = 15)
    private String productcode;

    public Long getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(Long ordernumber) {
        this.ordernumber = ordernumber;
    }

    public String getProductcode() {
        return productcode;
    }

    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderdetailId entity = (OrderdetailId) o;
        return Objects.equals(this.productcode, entity.productcode) &&
                Objects.equals(this.ordernumber, entity.ordernumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productcode, ordernumber);
    }

}