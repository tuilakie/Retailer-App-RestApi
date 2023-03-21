package com.ntneik15.selflearning.retailerapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PaymentId implements Serializable {
    private static final long serialVersionUID = 5947524740048525984L;
    @NotNull
    @Column(name = "customernumber", nullable = false)
    private Long customernumber;

    @Size(max = 50)
    @NotNull
    @Column(name = "checknumber", nullable = false, length = 50)
    private String checknumber;

    public Long getCustomernumber() {
        return customernumber;
    }

    public void setCustomernumber(Long customernumber) {
        this.customernumber = customernumber;
    }

    public String getChecknumber() {
        return checknumber;
    }

    public void setChecknumber(String checknumber) {
        this.checknumber = checknumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PaymentId entity = (PaymentId) o;
        return Objects.equals(this.customernumber, entity.customernumber) &&
                Objects.equals(this.checknumber, entity.checknumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customernumber, checknumber);
    }

}